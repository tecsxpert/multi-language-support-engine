import sys
import os

sys.path.append(os.path.dirname(__file__))
from groq_client import call_groq

result = call_groq("Explain AI in simple words")
print(result)