import React, { useState } from "react";
import { Layout, Menu, Typography, Button, AutoComplete, Input, Card, Row, Col } from "antd";
import { useNavigate } from "react-router-dom";
import {
  BookOutlined,
  ApartmentOutlined,
  UserOutlined,
  SearchOutlined,
  HeartOutlined,
  StarOutlined,
  FileTextOutlined,
  EyeOutlined,
  CheckCircleOutlined
} from "@ant-design/icons";
import mockData from "../mockData";
import logo from "../assets/sufeedback.png";

const { Header, Sider, Content } = Layout;
const { Title, Paragraph } = Typography;

const Main = () => {
  const navigate = useNavigate();
  const [searchValue, setSearchValue] = useState("");

  // Define department keys explicitly
  const departments = [
    { key: "CS", name: "Computer Science" },
    { key: "EE", name: "Electrical Engineering" },
    { key: "IE", name: "Industrial Engineering" },
    { key: "ME", name: "Mechanical Engineering" },
    { key: "NANO", name: "Nanoengineering" },
    { key: "ECON", name: "Economics" },
    { key: "PS", name: "Political Science" },
    { key: "CULT", name: "Cultural Studies" },
    { key: "GRS", name: "Gender Studies" },
    { key: "PSY", name: "Psychology" },
  ];

  // Filter only courses that have reviews
  const reviewedCourses = mockData.filter((course) => course.reviews && course.reviews.length > 0);

  // Search function
  const handleSearch = (value) => {
    setSearchValue(value);
  };

  return (
    <Layout style={{ minHeight: "100vh", backgroundColor: "#F8FAFC" }}>
      {/* Header */}
      <Header
        style={{
          backgroundColor: "#0A2540",
          display: "flex",
          justifyContent: "space-between",
          alignItems: "center",
          padding: "0 20px",
        }}
      >
        <div style={{ display: "flex", alignItems: "center" }}>
          <img src={logo} alt="SUFeedback Logo" style={{ height: "40px", marginRight: "15px" }} />
          <Title level={3} style={{ color: "#fff", margin: 0, fontWeight: "bold" }}>
            SUFeedback - Doğru Ders Seçimi için Güvenilir Rehber
          </Title>
        </div>
        <div>
          <Button
            type="link"
            style={{ color: "#FFD700", marginRight: "15px", fontWeight: "500" }}
            onClick={() => navigate("/about")}
          >
            About
          </Button>
          <Button
            type="primary"
            style={{
              background: "linear-gradient(to right, #007BFF, #0056D2)",
              border: "none",
              fontWeight: "500",
              padding: "5px 15px",
              borderRadius: "6px",
            }}
            onClick={() => navigate("/login")}
          >
            Login
          </Button>
        </div>
      </Header>

      <Layout>
        {/* Sidebar */}
        <Sider
          style={{
            backgroundColor: "#E2E8F0",
            padding: "15px",
            borderRadius: "10px",
            boxShadow: "2px 2px 10px rgba(0, 0, 0, 0.1)",
          }}
          width={280}
        >
          {/* Search Bar */}
          <AutoComplete
            placeholder="Search for courses..."
            value={searchValue}
            onChange={handleSearch}
            style={{ width: "100%", marginBottom: "15px", borderRadius: "8px" }}
            allowClear
          >
            <Input prefix={<SearchOutlined />} />
          </AutoComplete>

          <Menu mode="inline" style={{ borderRight: 0, fontWeight: "500" }}>
            <Menu.Item key="1" icon={<BookOutlined />} onClick={() => navigate("/common-core")}>
              Common Core Courses
            </Menu.Item>
            <Menu.Item key="2" icon={<HeartOutlined />} onClick={() => navigate("/favourites")}>
              Favourites
            </Menu.Item>

            {/* Engineering Departments */}
            <Menu.SubMenu key="engineering" title="School of Engineering" icon={<ApartmentOutlined />}>
              {departments
                .filter((dept) => ["CS", "EE", "IE", "ME", "NANO"].includes(dept.key))
                .map((dept) => (
                  <Menu.Item key={dept.key} onClick={() => navigate(`/department/${dept.key}`)}>
                    {dept.name}
                  </Menu.Item>
                ))}
            </Menu.SubMenu>

            {/* Social Sciences Departments */}
            <Menu.SubMenu key="social" title="Art and Social Sciences" icon={<UserOutlined />}>
              {departments
                .filter((dept) => ["ECON", "PS", "CULT", "GRS", "PSY"].includes(dept.key))
                .map((dept) => (
                  <Menu.Item key={dept.key} onClick={() => navigate(`/department/${dept.key}`)}>
                    {dept.name}
                  </Menu.Item>
                ))}
            </Menu.SubMenu>
          </Menu>
        </Sider>

        {/* Main Content */}
        <Layout style={{ padding: "24px" }}>
          <Content style={{ backgroundColor: "#fff", padding: "24px", borderRadius: "8px" }}>
            {/* Feature Cards */}
            <Row gutter={[16, 16]} justify="center">
              <Col span={6}>
                <Card
                  bordered
                  hoverable
                  onClick={() => navigate("/rate-review")}
                  style={{ textAlign: "center", backgroundColor: "#E3F2FD", borderRadius: "10px", padding: "20px" }}
                >
                  <StarOutlined style={{ fontSize: "32px", color: "#007BFF" }} />
                  <Title level={5} style={{ marginTop: "10px" }}>RATE & REVIEW COURSES</Title>
                  <Paragraph>“Share your feedback and help others make informed choices.”</Paragraph>
                </Card>
              </Col>
              <Col span={6}>
                <Card
                  bordered
                  hoverable
                  onClick={() => navigate("/explore-syllabus")}
                  style={{ textAlign: "center", backgroundColor: "#E8F5E9", borderRadius: "10px", padding: "20px" }}
                >
                  <FileTextOutlined style={{ fontSize: "32px", color: "#388E3C" }} />
                  <Title level={5} style={{ marginTop: "10px" }}>EXPLORE SYLLABUS LINKS</Title>
                  <Paragraph>“Access official course syllabus links directly from BannerWeb.”</Paragraph>
                </Card>
              </Col>
              <Col span={6}>
                <Card
                  bordered
                  hoverable
                  onClick={() => navigate("/view-feedback")}
                  style={{ textAlign: "center", backgroundColor: "#FFF3E0", borderRadius: "10px", padding: "20px" }}
                >
                  <EyeOutlined style={{ fontSize: "32px", color: "#F57C00" }} />
                  <Title level={5} style={{ marginTop: "10px" }}>VIEW RATINGS & FEEDBACK</Title>
                  <Paragraph>“Browse professor ratings, workload insights, and student reviews.”</Paragraph>
                </Card>
              </Col>
              <Col span={6}>
                <Card  //cards changed
                  bordered
                  hoverable
                  onClick={() => navigate("/sentiment-reviews")}
                  style={{ textAlign: "center", backgroundColor: "#F3E5F5", borderRadius: "10px", padding: "20px" }}
                >
                  <CheckCircleOutlined style={{ fontSize: "32px", color: "#8E24AA" }} />
                  <Title level={5} style={{ marginTop: "10px" }}>SENTIMENT-PROTECTED REVIEWS</Title>
                  <Paragraph>“Ensure respectful and constructive feedback with AI moderation.”</Paragraph>
                </Card>
              </Col>
            </Row>

            {/* Popular Reviews Section */}
            <Title level={4} style={{ marginTop: "24px", color: "#374151" }}>Popular Reviews</Title>
            <Row gutter={[16, 16]}>
              {reviewedCourses.map((course, index) => (
                <Col span={12} key={index}>
                  <Card
                    title={course.course}
                    bordered
                    hoverable
                    onClick={() => navigate(`/course/${course.course.replace(/\s/g, "").toLowerCase()}`)}
                  >
                    <Paragraph>{course.reviews[0]?.content || "No review available."}</Paragraph>
                  </Card>
                </Col>
              ))}
            </Row>
          </Content>
        </Layout>
      </Layout>
    </Layout>
  );
};

export default Main;
