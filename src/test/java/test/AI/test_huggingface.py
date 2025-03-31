from transformers import pipeline

# Loading a pre-trained model to analyze text sentiment
classifier = pipeline("text-classification", model="nlptown/bert-base-multilingual-uncased-sentiment")

# Testing on the example of vacancies
job1 = "We are looking for an AQA with Selenium experience."
job2 = "Marketing specialist needed for social media management."

result1 = classifier(job1)
result2 = classifier(job2)

print("Result 1:", result1)
print("Result 2:", result2)