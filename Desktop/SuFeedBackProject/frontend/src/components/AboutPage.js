import React from "react";
import { Layout, Typography, Card, Button } from "antd";
import { useNavigate } from "react-router-dom";

const { Header, Content } = Layout;
const { Title, Paragraph } = Typography;

const AboutPage = () => {
  const navigate = useNavigate();

  return (
    <Layout style={{ minHeight: "100vh" }}>
      {/* Header */}
      <Header
        style={{
          backgroundColor: "#001529",
          display: "flex",
          justifyContent: "space-between",
          alignItems: "center",
          padding: "0 20px",
        }}
      >
        <Title level={3} style={{ color: "#fff", margin: 0 }}>About SUFeedback</Title>
        <Button type="primary" onClick={() => navigate("/")}>Back to Home</Button>
      </Header>

      {/* Content */}
      <Content style={{ padding: "24px", maxWidth: "800px", margin: "0 auto" }}>
        <Card>
          <Title level={2}>Welcome to SUFeedback</Title>
          <Paragraph>
            SUFeedback is a platform designed to help students make informed choices about their courses and instructors. 
            It provides course reviews, instructor ratings, workload insights, and syllabus links to ensure a smooth academic experience.
          </Paragraph>

          <Title level={3}>📌 What You Can Do Here?</Title>
          <ul>
            <li><strong>📖 Explore Syllabi:</strong> View course syllabi directly.</li>
            <li><strong>⭐ Rate & Review Courses:</strong> Share feedback on courses and professors.</li>
            <li><strong>📊 See Ratings & Feedback:</strong> Check workload, grading style, and content quality.</li>
            <li><strong>🔍 Search Courses by Department:</strong> Browse by faculty and find detailed reviews.</li>
          </ul>

          <Title level={3}>🎯 Our Goal</Title>
          <Paragraph>
            We aim to provide transparency in course selection and help students make better academic decisions based on real feedback.
          </Paragraph>

          <Button type="primary" onClick={() => navigate("/")}>Back to Home</Button>
        </Card>
      </Content>
    </Layout>
  );
};

export default AboutPage;
