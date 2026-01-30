export default function CodeDiff({ original, corrected }) {
  return (
    <div className="diff-container">
      <div className="diff-box">
        <h4>📝 Original Code</h4>
        <pre>{original}</pre>
      </div>


      <div className="diff-box">
        <h4>✨ Corrected Code</h4>
        <pre>{corrected}</pre>
      </div>
    </div>
  );
}
