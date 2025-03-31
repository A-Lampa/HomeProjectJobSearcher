from transformers import BertTokenizer, Trainer, TrainingArguments
from model import create_model
from dataset import load_dataset
import pandas as pd
from sklearn.metrics import classification_report
from sklearn.metrics import classification_report, confusion_matrix, ConfusionMatrixDisplay
import matplotlib.pyplot as plt

# Assembly of the entire pipeline - from loading data to training and saving the model

def main():
    tokenizer = BertTokenizer.from_pretrained("bert-base-uncased")
    train_dataset, val_dataset = load_dataset("vacancies_dataset.csv", tokenizer)

    # Check class balance:
    df = pd.read_csv("vacancies_dataset.csv")
    print(df['Score'].value_counts())

    model = create_model()

    training_args = TrainingArguments(
        output_dir="./results",
        evaluation_strategy="epoch",
        learning_rate=2e-5,
        per_device_train_batch_size=8,
        per_device_eval_batch_size=8,
        num_train_epochs=3,
        weight_decay=0.01,
        logging_dir="./logs",
        logging_steps=10,
        save_strategy="epoch",
    )

    trainer = Trainer(
        model=model,
        args=training_args,
        train_dataset=train_dataset,
        eval_dataset=val_dataset,
    )

    trainer.train()
    trainer.save_model("./saved_model")

    # Show quality metrics: precision (accuracy) — how many "1"s are predicted correctly;
    # recall — how well the model finds all the real "1"; f1-score — balance of precision and recall.
    preds = trainer.predict(val_dataset)
    y_pred = preds.predictions.argmax(-1)
    y_true = preds.label_ids
    print(classification_report(y_true, y_pred))

    # Predictions on the validation dataset:
    preds = trainer.predict(val_dataset)
    y_pred = preds.predictions.argmax(-1)
    y_true = preds.label_ids

    # Report of the predictions on the validation dataset:
    print(classification_report(y_true, y_pred))

    # Confusion matrix
    cm = confusion_matrix(y_true, y_pred, labels=[0, 1])
    disp = ConfusionMatrixDisplay(confusion_matrix=cm, display_labels=[0, 1])
    disp.plot(cmap=plt.cm.Blues)
    plt.title("Confusion Matrix")
    plt.grid(False)
    plt.show()

if __name__ == "__main__":
    main()
