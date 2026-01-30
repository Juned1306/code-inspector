import { useState } from "react";
import ResultPanel from "../components/ResultPanel";


export default function Inspector() {
  const [code, setCode] = useState("");
  const [result, setResult] = useState(null);
  const [loading, setLoading] = useState(false);


  const inspectCode = async () => {
    if (!code.trim()) return alert("Please enter some code.");


    setLoading(true);
    setResult(null);


    try {
      const response = await fetch("http://localhost:8080/api/code", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ code }),
      });


      if (!response.ok) throw new Error("Failed to inspect code.");


      const data = await response.json();
      setResult({ ...data, originalCode: code }); // include original code
    } catch (err) {
      alert(err.message);
    } finally {
      setLoading(false);
    }
  };


  const clearScreen = () => {
    setCode("");
    setResult(null);
  };


  return (
    <div className="inspector">
      <h2>AI Code Inspector</h2>
      <textarea
        rows="12"
        value={code}
        onChange={(e) => setCode(e.target.value)}
        placeholder="Paste your code here..."
      />
      <div style={{ marginTop: "10px" }}>
        <button onClick={inspectCode} disabled={loading}>
          {loading ? "Inspecting..." : "Inspect Code"}
        </button>
        <button
          onClick={clearScreen}
          style={{ marginLeft: "10px", background: "#ff3b30" }}
        >
          Clear
        </button>
      </div>


      <ResultPanel result={result} code={code} />
    </div>
  );
}
