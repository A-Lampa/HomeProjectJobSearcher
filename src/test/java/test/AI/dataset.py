import pandas as pd
import torch
from sklearn.model_selection import train_test_split
from transformers import AutoTokenizer

tokenizer = AutoTokenizer.from_pretrained("nlptown/bert-base-multilingual-uncased-sentiment")

def load_data(csv_path):
    df = pd.read_csv(csv_path)

    train_texts, test_texts, train_labels, test_labels = train_test_split(
        df["Vacancy"].tolist(), df["Score"].tolist(), test_size=0.2, random_state=42, stratify=labels
    )

    train_encodings = tokenizer(train_texts, truncation=True, padding=True, max_length=512)
    test_encodings = tokenizer(test_texts, truncation=True, padding=True, max_length=512)

    return (train_encodings, train_labels), (test_encodings, test_labels)

class JobDataset(torch.utils.data.Dataset):
    def __init__(self, encodings, labels):
        self.encodings = encodings
        self.labels = labels

    def __len__(self):
        return len(self.labels)

    def __getitem__(self, idx):
        item = {key: torch.tensor(val[idx]) for key, val in self.encodings.items()}
        item["labels"] = torch.tensor(self.labels[idx])
        return item
