import React from "react";
import { useNavigate } from "react-router-dom";
import { Button, Form, Input } from "antd";
import logo from "../assets/sufeedback.png";

const SignupPage = () => {
  const navigate = useNavigate(); // navigate fonksiyonu

  const onFinish = (values) => {
    console.log("Signup Form Submitted:", values);
  };

  return (
    <div style={{ minHeight: "100vh", backgroundColor: "#f5f5f5" }}>
      {/* Üst Bar */}
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

      {/* Signup Container */}
      <div
        style={{
          display: "flex",
          justifyContent: "center",
          alignItems: "center",
          minHeight: "100vh",
          paddingTop: "100px", // Üst bar için ayarlandı
        }}
      >
        <div
          style={{
            backgroundColor: "white",
            borderRadius: "8px",
            padding: "40px",
            boxShadow: "0 2px 10px rgba(0, 0, 0, 0.1)",
            maxWidth: "400px",
            width: "100%",
            textAlign: "center",
          }}
        >
          <h2 style={{ fontWeight: "bold", marginBottom: "20px" }}>
            LET'S HELP EACH OTHER!
          </h2>
          <div
            style={{
              display: "flex",
              justifyContent: "center",
              marginBottom: "20px",
            }}
          >
            <Button
              style={{
                marginRight: "10px",
                backgroundColor: "transparent",
                color: "#001529",
                border: "1px solid #001529",
              }}
              onClick={() => navigate("/")}
            >
              Login
            </Button>
            <Button
              style={{
                backgroundColor: "#001529",
                color: "white",
                border: "none",
              }}
              onClick={() => navigate("/signup")}
            >
              Signup
            </Button>
          </div>
          <Form
            name="signup"
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
                  borderRadius: "4px",
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
                  borderRadius: "4px",
                  border: "1px solid #ddd",
                }}
              />
            </Form.Item>
            <Form.Item
              name="confirmPassword"
              rules={[{ required: true, message: "Please confirm your password!" }]}
            >
              <Input.Password
                placeholder="Confirm password"
                style={{
                  padding: "10px",
                  borderRadius: "4px",
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
                  padding: "10px",
                  borderRadius: "4px",
                  fontSize: "16px",
                  fontWeight: "bold",
                }}
              >
                Signup
              </Button>
            </Form.Item>
          </Form>
        </div>
      </div>
    </div>
  );
};

export default SignupPage;
