import torch
from transformers import Trainer, TrainingArguments
from torch.utils.data import DataLoader
from dataset import load_data, JobDataset
from model import create_model

(train_encodings, train_labels), (test_encodings, test_labels) = load_data("vacancies_dataset.csv")

train_dataset = JobDataset(train_encodings, train_labels)
test_dataset = JobDataset(test_encodings, test_labels)

model = create_model()

training_args = TrainingArguments(
    output_dir="./results",
    evaluation_strategy="epoch",
    save_strategy="epoch",
    per_device_train_batch_size=8,
    per_device_eval_batch_size=8,
    num_train_epochs=3,
    logging_dir="./logs"
)

trainer = Trainer(
    model=model,
    args=training_args,
    train_dataset=train_dataset,
    eval_dataset=test_dataset
)

trainer.train()
trainer.save_model("./trained_model")
