import chromadb
from chromadb.config import Settings

# Initialize Chroma
client = chromadb.Client(Settings(persist_directory="./chroma_data"))

collection = client.get_or_create_collection(name="knowledge")


# ✅ Load knowledge from file (run once)
def load_knowledge():
    with open("data/knowledge.txt", "r") as f:
        docs = f.readlines()

    for i, doc in enumerate(docs):
        collection.add(
            documents=[doc.strip()],
            ids=[str(i)]
        )

    print("✅ Knowledge loaded into ChromaDB")


# ✅ Retrieve relevant context
def get_context(query):
    results = collection.query(
        query_texts=[query],
        n_results=3
    )

    docs = results.get("documents", [[]])[0]

    return "\n".join(docs)