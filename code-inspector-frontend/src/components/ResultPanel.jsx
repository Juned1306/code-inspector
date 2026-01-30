import CodeDiff from "./CodeDiff";


export default function ResultPanel({ result, code }) {
  if (!result) return null;


  return (
    <div className="result-box">
      <h3>AI Inspection Result</h3>


      <section>
        <h4>❌ Errors</h4>
        {result.errors && result.errors.length > 0 ? (
          <ul>
            {result.errors.map((e, i) => (
              <li key={i}>{e}</li>
            ))}
          </ul>
        ) : (
          <p>No errors found 🎉</p>
        )}
      </section>


      <section>
        <h4>💡 Suggestions</h4>
        {result.suggestions && result.suggestions.length > 0 ? (
          <ul>
            {result.suggestions.map((s, i) => (
              <li key={i}>{s}</li>
            ))}
          </ul>
        ) : (
          <p>No suggestions</p>
        )}
      </section>


      <CodeDiff
        original={result.originalCode ?? code ?? "Original code not available"}
        corrected={result.correctCode ?? result.originalCode ?? code ?? ""}
      />


      <section>
        <h4>🧠 Explanation</h4>
        <p>{result.explanation ?? "No explanation provided"}</p>
      </section>
    </div>
  );
}
