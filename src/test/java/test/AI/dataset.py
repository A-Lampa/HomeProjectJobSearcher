import pandas as pd
import torch
from transformers import BertTokenizer

# Data processing, dataset creation and division into training/validation samples

class VacancyDataset(torch.utils.data.Dataset):
    def __init__(self, texts, labels, tokenizer, max_length=256):
        self.encodings = tokenizer(texts, truncation=True, padding=True, max_length=max_length)
        self.labels = labels

    def __getitem__(self, idx):
        item = {k: torch.tensor(v[idx]) for k, v in self.encodings.items()}
        item["labels"] = torch.tensor(self.labels[idx])
        return item

    def __len__(self):
        return len(self.labels)

def load_dataset(path, tokenizer, test_size=0.1):
    df = pd.read_csv(path)
    texts = df["Vacancy"].tolist()
    labels = df["Score"].tolist()

    from sklearn.model_selection import train_test_split
    train_texts, val_texts, train_labels, val_labels = train_test_split(
        texts, labels, test_size=test_size, random_state=42
    )

    train_dataset = VacancyDataset(train_texts, train_labels, tokenizer)
    val_dataset = VacancyDataset(val_texts, val_labels, tokenizer)
    return train_dataset, val_dataset