export const sendEmail = async (emailData) => {

  const response = await fetch("http://localhost:8080/notifications/email", {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(emailData)
  });

  return response;
};