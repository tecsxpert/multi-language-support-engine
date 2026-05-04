import { useEffect, useState } from "react";
import { BarChart, Bar, XAxis, YAxis, Tooltip } from "recharts";
import "./index.css";

function App() {

  const [data, setData] = useState([]);
  const [stats, setStats] = useState({ total: 0, english: 0 });

  const [title, setTitle] = useState("");
  const [language, setLanguage] = useState("");
  const [editId, setEditId] = useState(null);

  const [search, setSearch] = useState("");

  const [aiResponse, setAiResponse] = useState(null);
  const [loading, setLoading] = useState(false);

  const API = "http://localhost:8080/api/records";

  // ======================
  // FETCH
  // ======================
  const fetchData = () => {
    fetch(API)
      .then(res => res.json())
      .then(setData);
  };

  const fetchStats = () => {
    fetch(`${API}/stats`)
      .then(res => res.json())
      .then(setStats);
  };

  useEffect(() => {
    fetchData();
    fetchStats();
  }, []);

  // ======================
  // SEARCH (Debounce)
  // ======================
  useEffect(() => {
    const delay = setTimeout(() => {
      if (search.trim().length < 2) {
        fetchData();
        return;
      }

      fetch(`${API}/search?q=${search}`)
        .then(res => res.json())
        .then(setData);

    }, 500);

    return () => clearTimeout(delay);
  }, [search]);

  // ======================
  // CRUD
  // ======================
  const handleAdd = () => {
    fetch(API, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ title, language })
    }).then(() => {
      fetchData();
      fetchStats();
      setTitle("");
      setLanguage("");
      setAiResponse(null);   // ✅ clear AI
    });
  };

  const handleDelete = (id) => {
    fetch(`${API}/${id}`, { method: "DELETE" })
      .then(() => {
        fetchData();
        fetchStats();
        setAiResponse(null); // optional cleanup
      });
  };

  const handleEdit = (item) => {
    setEditId(item.id);
    setTitle(item.title);
    setLanguage(item.language);
    setAiResponse(null); // clear AI on edit
  };

  const handleUpdate = () => {
    fetch(`${API}/${editId}`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ title, language })
    }).then(() => {
      setEditId(null);
      setTitle("");
      setLanguage("");
      fetchData();
      fetchStats();
      setAiResponse(null);   // ✅ clear AI
    });
  };

  // ======================
  // 🤖 AI
  // ======================
  const handleAI = async () => {
    setLoading(true);
    setAiResponse(null);

    try {
      const res = await fetch(`${API}/ai/describe`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify({
          title,
          language
        })
      });

      const data = await res.json();
      setAiResponse(data);

    } catch (err) {
      console.error("AI ERROR:", err);
      alert("AI failed ❌");
    }

    setLoading(false);
  };

  const chartData = [
    { name: "Total", value: stats.total },
    { name: "English", value: stats.english }
  ];

  return (
    <div className="container">

      <h1>Multi-Language Support Engine</h1>
      <h2>Day 8 — AI Panel 🚀</h2>

      {/* KPI */}
      <div className="cards">
        <div className="card">
          <h3>Total</h3>
          <p>{stats.total}</p>
        </div>
        <div className="card">
          <h3>English</h3>
          <p>{stats.english}</p>
        </div>
      </div>

      {/* Chart */}
      <BarChart width={400} height={300} data={chartData}>
        <XAxis dataKey="name" />
        <YAxis />
        <Tooltip />
        <Bar dataKey="value" fill="#4F46E5" />
      </BarChart>

      {/* SEARCH */}
      <input
        placeholder="Search..."
        value={search}
        onChange={(e) => setSearch(e.target.value)}
      />

      {/* FILTER */}
      <select onChange={(e) => {
        const lang = e.target.value;
        if (!lang) fetchData();
        else {
          fetch(`${API}/filter?lang=${lang}`)
            .then(res => res.json())
            .then(setData);
        }
      }}>
        <option value="">All</option>
        <option value="English">English</option>
        <option value="Spanish">Spanish</option>
      </select>

      {/* FORM */}
      <div className="form">
        <input
          placeholder="Title"
          value={title}
          onChange={(e) => setTitle(e.target.value)}
        />
        <input
          placeholder="Language"
          value={language}
          onChange={(e) => setLanguage(e.target.value)}
        />

        <button onClick={editId ? handleUpdate : handleAdd}>
          {editId ? "Update" : "Add"}
        </button>

        {/* ✅ Disabled AI Button */}
        <button
          onClick={handleAI}
          disabled={!title || !language || loading}
        >
          {loading ? "Generating..." : "Generate AI"}
        </button>
      </div>

      {/* AI PANEL */}
      {loading && <p>⏳ Generating AI...</p>}

      {aiResponse && (
        <div className="card">
          <h3>AI Output</h3>
          <p>{aiResponse.description}</p>
          <small>{aiResponse.generated_at}</small>
        </div>
      )}

      <hr />

      {/* LIST */}
      {data.map(item => (
        <div key={item.id} className="list-item">
          <p>
            {item.title} - {
              item.language?.charAt(0).toUpperCase() +
              item.language?.slice(1)
            }
          </p>

          <button onClick={() => handleEdit(item)}>Edit</button>
          <button onClick={() => handleDelete(item.id)}>Delete</button>
        </div>
      ))}

    </div>
  );
}

export default App;