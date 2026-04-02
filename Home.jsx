import EmailForm from "../components/EmailForm";

function Home() {

  return (
  <div id="app-container">
    <h1 style={{ marginBottom: "0" }}>
      Email Notifications on WhatsApp
    </h1>
    <EmailForm />
  </div>
);

}

export default Home;