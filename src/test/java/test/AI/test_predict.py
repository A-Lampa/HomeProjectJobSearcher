import torch
from transformers import AutoModelForSequenceClassification, AutoTokenizer

model_path = "./trained_model"
tokenizer = AutoTokenizer.from_pretrained("nlptown/bert-base-multilingual-uncased-sentiment")
model = AutoModelForSequenceClassification.from_pretrained(model_path)

def predict(vacancies):
    encodings = tokenizer(vacancies, truncation=True, padding=True, max_length=512, return_tensors="pt")
    model.eval()

    with torch.no_grad():
        outputs = model(**encodings)
        predictions = torch.argmax(outputs.logits, dim=1)

    return predictions.tolist()

# Example
test_vacancies = [
    "QA Automation Engineer with Java",
    "Graphic Designer - Remote",
    "Senior Java Developer with Selenium"
]

results = predict(test_vacancies)
for vacancy, score in zip(test_vacancies, results):
    print(f"Vacancy: {vacancy} | Predicted Score: {score}")
