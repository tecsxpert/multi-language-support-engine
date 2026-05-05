from services.groq_client import call_groq

def generate_description(input_text):
    prompt = f"Describe: {input_text}"
    return call_groq(prompt)

def generate_recommendation(input_text):
    prompt = f"Recommend actions for: {input_text}"
    return call_groq(prompt)

def generate_report(input_text):
    prompt = f"Generate report for: {input_text}"
    return call_groq(prompt)