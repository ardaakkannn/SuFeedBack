import React from "react";
import { useNavigate } from "react-router-dom";
import { Button, Form, Input } from "antd";
import logo from "../assets/sufeedback.png";

const LoginPage = () => {
  const navigate = useNavigate(); // Navigate fonksiyonu

  const handleClick = (path) => {
    navigate(path); // Navigasyon fonksiyonu
  };

  const onFinish = (values) => {
    console.log("Login Form Submitted:", values);
  };

  return (
    <div style={{ minHeight: "100vh", backgroundColor: "#f5f5f5" }}>
      {/* Top Bar */}
      <div
        style={{
          display: "flex",
          justifyContent: "space-between",
          alignItems: "center",
          width: "100%",
          padding: "20px",
          backgroundColor: "#001529",
          color: "white",
          position: "fixed",
          top: 0,
          left: 0,
          zIndex: 1000,
        }}
      >
        <div style={{ display: "flex", alignItems: "center" }}>
          <img
            src={logo}
            alt="Logo"
            style={{ height: "50px", marginRight: "15px" }}
          />
          <div>
            <h1 style={{ margin: 0, color: "white" }}>SUFeedback</h1>
            <p style={{ margin: 0, fontSize: "14px", color: "lightgray" }}>
              Doğru Ders Seçimi İçin Güvenilir Rehber
            </p>
          </div>
        </div>
        <Button
          style={{
            color: "white",
            backgroundColor: "transparent",
            border: "1px solid white",
          }}
        >
          TR/ENG
        </Button>
      </div>

      {/* Login/Signup Container */}
      <div
        style={{
          display: "flex",
          justifyContent: "center",
          alignItems: "center",
          minHeight: "100vh",
          paddingTop: "120px", // Top bar ile uyumlu
        }}
      >
        <div
          style={{
            backgroundColor: "white",
            borderRadius: "10px",
            padding: "30px 40px",
            boxShadow: "0px 4px 15px rgba(0, 0, 0, 0.2)",
            maxWidth: "400px",
            width: "100%",
            textAlign: "center",
          }}
        >
          <h2 style={{ fontWeight: "bold", marginBottom: "20px" }}>WELCOME!</h2>
          <div
            style={{
              display: "flex",
              justifyContent: "space-between",
              marginBottom: "20px",
            }}
          >
            <Button
              style={{
                flex: 1,
                marginRight: "10px",
                backgroundColor: "#001529",
                color: "white",
                border: "none",
                borderRadius: "5px",
              }}
              onClick={() => handleClick("/login")}
            >
              Login
            </Button>
            <Button
              style={{
                flex: 1,
                backgroundColor: "transparent",
                color: "#001529",
                border: "1px solid #001529",
                borderRadius: "5px",
              }}
              onClick={() => handleClick("/signup")}
            >
              Signup
            </Button>
          </div>
          <Form
            name="login"
            onFinish={onFinish}
            layout="vertical"
            style={{ textAlign: "left" }}
          >
            <Form.Item
              name="email"
              rules={[{ required: true, message: "Please enter your email!" }]}
            >
              <Input
                placeholder="Sabancı University mail"
                style={{
                  padding: "10px",
                  borderRadius: "5px",
                  border: "1px solid #ddd",
                }}
              />
            </Form.Item>

            <Form.Item
              name="password"
              rules={[{ required: true, message: "Please enter your password!" }]}
            >
              <Input.Password
                placeholder="Password"
                style={{
                  padding: "10px",
                  borderRadius: "5px",
                  border: "1px solid #ddd",
                }}
              />
            </Form.Item>
            <Form.Item>
              <Button
                htmlType="submit"
                style={{
                  backgroundColor: "#001529",
                  color: "white",
                  border: "none",
                  width: "100%",
                  padding: "12px",
                  borderRadius: "5px",
                  fontSize: "16px",
                  fontWeight: "bold",
                }}
              >
                Login
              </Button>
            </Form.Item>
          </Form>
        </div>
      </div>
    </div>
  );
};

export default LoginPage;
