export default function CodeEditor({ code, setCode }) {
  return (
    <textarea
      value={code}
      onChange={(e) => setCode(e.target.value)}
      rows={12}
      placeholder="Paste your code here..."
      style={{ width: "100%" }}
    />
  );
}
