import React from "react";
import { useParams, useNavigate } from "react-router-dom";
import { Layout, Row, Col, Card, Typography, Button, Avatar, Tag } from "antd";
import { HomeOutlined } from "@ant-design/icons";
import mockData from "../mockData";

const { Title } = Typography;
const { Content } = Layout;
const { Meta } = Card;

const DepartmentPage = () => {
  const { departmentName } = useParams();
  const navigate = useNavigate();

  // Filter courses belonging to the selected department
  const departmentCourses = mockData.filter(
    (course) => course.department.toLowerCase() === departmentName.toLowerCase()
  );

  return (
    <Layout style={{ padding: "24px", maxWidth: "1200px", margin: "0 auto" }}>
      <Content>
        <Title level={2}>{departmentName.toUpperCase()} - Courses</Title>
        <Row gutter={[16, 16]}>
          {departmentCourses.map((course) => (
            <Col xs={24} sm={12} md={8} lg={6} key={course.course}>
              <div
                onClick={() => navigate(`/course/${course.course}`)}
                style={{ cursor: "pointer" }}
              >
                <Card
                  hoverable
                  cover={
                    <img
                      alt={course.course}
                      src={course.image}
                      style={{ height: "150px", objectFit: "cover" }}
                    />
                  }
                  style={{ borderRadius: "8px", overflow: "hidden" }}
                >
                  <Meta
                    avatar={
                      <Avatar src={course.instructorAvatar}>
                        {course.instructor[0]}
                      </Avatar>
                    }
                    title={course.course}
                    description={`Instructor: ${course.instructor}`}
                  />
                  <div style={{ marginTop: "10px" }}>
                    {course.tags.map((tag) => (
                      <Tag key={tag} color="blue">
                        {tag}
                      </Tag>
                    ))}
                  </div>
                </Card>
              </div>
            </Col>
          ))}
        </Row>
        <Button
          type="primary"
          icon={<HomeOutlined />}
          style={{ marginTop: "20px" }}
          onClick={() => navigate("/")}
        >
          Back to Home
        </Button>
      </Content>
    </Layout>
  );
};

export default DepartmentPage;
