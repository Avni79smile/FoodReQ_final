# ML Service
Port 8090.

## Purpose
Predicts the probability that a recipient will accept and successfully complete a food donation.

## Features
distance_km, expiry_hours, quantity_ratio, urgency_score, pickup_available, recipient_capacity_kg, historical_acceptance_rate.

## Model
Random Forest classifier from scikit-learn.

## Train
`python train_model.py`

The included starter dataset is clearly labeled as prototype/demo data. Replace it with ethically sourced real historical/public data for a research-grade model and report its source.

## API
POST `/predict`
GET `/health`
GET `/model-info`
