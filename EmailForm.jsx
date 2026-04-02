import { useState } from "react";
import { sendEmail } from "../services/api";

function EmailForm() {
  const [sender, setSender] = useState("");
  const [subject, setSubject] = useState("");
  const [body, setBody] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();

    const emailData = {
      sender,
      subject,
      body,
    };

    try {
      const response = await sendEmail(emailData);
      const result = await response.text();
      alert(result);
    } catch (error) {
      alert("Error sending notification");
    }
  };

  return (
    <div style={styles.container}>
      <form onSubmit={handleSubmit} style={styles.form}>
        <h2>Send Notification</h2>

        <label>Sender Email</label>
        <input
          type="text"
          value={sender}
          onChange={(e) => setSender(e.target.value)}
          style={styles.input}
        />

        <label>Subject</label>
        <input
          type="text"
          value={subject}
          onChange={(e) => setSubject(e.target.value)}
          style={styles.input}
        />

        <label>Message</label>
        <textarea
          value={body}
          onChange={(e) => setBody(e.target.value)}
          style={styles.textarea}
        />

        <button type="submit" style={styles.button}>
          Send Notification
        </button>
      </form>
    </div>
  );
}

const styles = {
  container: {
    display: "flex",
    justifyContent: "center",   // horizontal center
    alignItems: "center",       // vertical center
    height: "100vh",
  },
  form: {
    width: "320px",
  },
  input: {
    width: "100%",
    padding: "8px",
    margin: "8px 0",
  },
  textarea: {
    width: "100%",
    padding: "8px",
    margin: "8px 0",
  },
  button: {
    padding: "10px",
    width: "100%",
    cursor: "pointer",
  },
};

export default EmailForm;