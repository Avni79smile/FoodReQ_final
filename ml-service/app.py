from pathlib import Path
import joblib
import pandas as pd
from fastapi import FastAPI, HTTPException
from pydantic import BaseModel, Field

BASE = Path(__file__).parent
MODEL_PATH = BASE / "model" / "foodresq_acceptance_model.joblib"
app = FastAPI(title="FoodResQ ML Service", version="1.0.0")
bundle = joblib.load(MODEL_PATH) if MODEL_PATH.exists() else None

class Features(BaseModel):
    distance_km: float = Field(5, ge=0)
    expiry_hours: float = Field(12, ge=0)
    quantity_ratio: float = Field(1, ge=0)
    urgency_score: float = Field(70, ge=0, le=100)
    pickup_available: int = Field(1, ge=0, le=1)
    recipient_capacity_kg: float = Field(50, ge=0)
    historical_acceptance_rate: float = Field(0.7, ge=0, le=1)

@app.get("/health")
def health():
    return {"status":"UP", "model_loaded": bundle is not None}

@app.get("/model-info")
def model_info():
    return {
        "model":"RandomForestClassifier",
        "target":"recipient_acceptance",
        "features": bundle["features"] if bundle else [],
        "status":"trained" if bundle else "not-trained"
    }

@app.post("/predict")
def predict(f: Features):
    if bundle is None:
        raise HTTPException(503, "Model is not trained. Run train_model.py first.")
    data = pd.DataFrame([f.model_dump()])
    model = bundle["model"]
    probability = float(model.predict_proba(data)[0][1])
    return {
        "acceptance_probability": probability,
        "prediction": "LIKELY_ACCEPT" if probability >= 0.5 else "LOWER_LIKELIHOOD",
        "model": "RandomForestClassifier"
    }
