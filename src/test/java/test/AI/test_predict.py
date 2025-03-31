from transformers import BertTokenizer, BertForSequenceClassification
import torch

# Loading the trained model and class prediction for new examples

def predict(texts):
    tokenizer = BertTokenizer.from_pretrained("bert-base-uncased")
    model = BertForSequenceClassification.from_pretrained("./fine_tuned_model")
    model.eval()

    inputs = tokenizer(texts, return_tensors="pt", truncation=True, padding=True)
    with torch.no_grad():
        outputs = model(**inputs)
        predictions = torch.argmax(outputs.logits, dim=-1)
    return predictions.tolist()

# Example
if __name__ == "__main__":
    examples = [
        "QA Automation Engineer with experience in Selenium",
        "Graphic Designer with Figma skills",
        "QA Automation Engineer with Java",
        "Graphic Designer - Remote",
        "Senior Java Developer with Selenium",
        "Strong experience in Java and Selenium",
        "Graphic designer with knowledge of Adobe tools",
        "Automation QA engineer with API testing skills",
        "Linux system administrator with Docker and Kubernetes"
    ]
    results = predict(examples)
    for text, label in zip(examples, results):
        print(f"'{text}' => {label}")


