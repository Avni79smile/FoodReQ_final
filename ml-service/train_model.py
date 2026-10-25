from pathlib import Path
import pandas as pd
from sklearn.ensemble import RandomForestClassifier
from sklearn.model_selection import train_test_split
from sklearn.metrics import accuracy_score, classification_report
import joblib

BASE = Path(__file__).parent
data_path = BASE / "data" / "foodresq_training_data.csv"
model_path = BASE / "model" / "foodresq_acceptance_model.joblib"
model_path.parent.mkdir(exist_ok=True)

df = pd.read_csv(data_path)
features = [
    "distance_km","expiry_hours","quantity_ratio","urgency_score",
    "pickup_available","recipient_capacity_kg","historical_acceptance_rate"
]
X = df[features]
y = df["accepted"]

X_train, X_test, y_train, y_test = train_test_split(
    X, y, test_size=0.20, random_state=42, stratify=y
)
model = RandomForestClassifier(
    n_estimators=200, max_depth=7, random_state=42, class_weight="balanced"
)
model.fit(X_train, y_train)
pred = model.predict(X_test)
print("Accuracy:", round(accuracy_score(y_test, pred), 4))
print(classification_report(y_test, pred))
joblib.dump({"model": model, "features": features}, model_path)
print("Saved:", model_path)
