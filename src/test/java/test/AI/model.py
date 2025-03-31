from transformers import AutoModelForSequenceClassification

def create_model():
    model = AutoModelForSequenceClassification.from_pretrained("nlptown/bert-base-multilingual-uncased-sentiment",
    num_labels=2,
    ignore_mismatched_sizes=True)
    return model