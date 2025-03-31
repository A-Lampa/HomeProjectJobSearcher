from transformers import BertTokenizer, Trainer, TrainingArguments, BertForSequenceClassification
from dataset import VacancyDataset
import pandas as pd
import torch

# fine-tuning to train a model on hard examples after the base training

def load_hard_dataset(csv_path, tokenizer):
    df = pd.read_csv(csv_path)
    texts = df["Vacancy"].tolist()
    labels = df["Score"].tolist()
    dataset = VacancyDataset(texts, labels, tokenizer, max_length=256)
    return dataset

def main():
    # Load the model after the base training
    model = BertForSequenceClassification.from_pretrained("./saved_model")
    tokenizer = BertTokenizer.from_pretrained("bert-base-uncased")

    # Load hard-examples
    hard_dataset = load_hard_dataset("hard_examples_dataset.csv", tokenizer)

    # Settings
    training_args = TrainingArguments(
        output_dir="./fine_tuned_results",
        learning_rate=1e-5,             # less than base training
        per_device_train_batch_size=4,
        num_train_epochs=3,
        weight_decay=0.01,
        logging_dir="./fine_tuned_logs",
        logging_steps=5,
        save_strategy="no",
        warmup_steps=0,
        gradient_accumulation_steps=1
    )

    #Trainer
    trainer = Trainer(
        model=model,
        args=training_args,
        train_dataset=hard_dataset,
    )

    # Fine-tune
    trainer.train()

    # Save the model after fine-tuning
    trainer.save_model("./fine_tuned_model")

if __name__ == "__main__":
    main()