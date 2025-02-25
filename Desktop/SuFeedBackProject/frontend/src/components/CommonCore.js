import React, { useState, useEffect } from "react";
import { Layout, Typography, Select, Card, Row, Col, Divider, Tag, Input, Tree, Tooltip } from "antd";
import { BookOutlined, SearchOutlined, DownOutlined } from "@ant-design/icons";
import { useNavigate } from "react-router-dom";
import { debounce } from "lodash";

const { Header, Content } = Layout;
const { Title, Paragraph } = Typography;
const { Option } = Select;

const departmentCourses = {
  CS: [
    { code: "IF 100", name: "Computational Approaches", credits: 3, semester: "1st", prerequisites: [] },
    { code: "CS 201", name: "Introduction to Computing", credits: 4, semester: "1st", prerequisites: ["IF 100"] },
    { code: "CS 204", name: "Advanced Programming", credits: 4, semester: "2nd", prerequisites: ["CS 201"] },
    { code: "CS 300", name: "Data Structures", credits: 3, semester: "3rd", prerequisites: ["CS 204"] },
    { code: "CS 301", name: "Algorithms", credits: 3, semester: "4th", prerequisites: ["CS 300"] },
  ],
};

const CommonCore = () => {
  const [selectedDepartment, setSelectedDepartment] = useState("CS");
  const [searchTerm, setSearchTerm] = useState("");
  const navigate = useNavigate();

  const handleSearch = debounce((value) => {
    setSearchTerm(value);
  }, 300);

  useEffect(() => {
    return () => {
      handleSearch.cancel();
    };
  }, []);

  const filteredCourses = departmentCourses[selectedDepartment].filter((course) =>
    course.name.toLowerCase().includes(searchTerm.toLowerCase()) ||
    course.code.toLowerCase().includes(searchTerm.toLowerCase())
  );

  const generateTreeData = (courses) => {
    const courseMap = {};
    courses.forEach(course => {
      courseMap[course.code] = {
        title: `${course.code} - ${course.name}`,
        key: course.code,
        children: [],
      };
    });

    courses.forEach(course => {
      (course.prerequisites || []).forEach(prereq => {
        if (courseMap[prereq]) {
          courseMap[prereq].children.push(courseMap[course.code]);
        }
      });
    });

    return Object.values(courseMap).filter(course => !course.prerequisites || course.prerequisites.length === 0);
  };

  return (
    <Layout style={{ minHeight: "100vh", backgroundColor: "#F8FAFC" }}>
      <Header style={{ backgroundColor: "#0A2540", padding: "0 20px" }}>
        <Title level={3} style={{ color: "#fff", margin: 0, fontWeight: "bold" }}>
          Common Core Courses
        </Title>
      </Header>

      <Layout style={{ padding: "24px" }}>
        <Content style={{ backgroundColor: "#fff", padding: "24px", borderRadius: "8px" }}>
          <div style={{ marginBottom: "20px", textAlign: "center" }}>
            <Title level={4}>Select Your Department</Title>
            <Tooltip title="Select your department to view relevant courses">
              <Select defaultValue="CS" style={{ width: 250, fontSize: "16px", marginRight: "20px" }} onChange={setSelectedDepartment}>
                <Option value="CS">Computer Science</Option>
              </Select>
            </Tooltip>
            <Input placeholder="Search courses..." prefix={<SearchOutlined />} style={{ width: 250, fontSize: "16px" }} onChange={(e) => handleSearch(e.target.value)} />
          </div>
          <Divider />

          <Row gutter={[16, 16]}>
            {filteredCourses.map((course, index) => (
              <Col xs={24} sm={12} md={8} lg={6} xl={6} key={index}>
                <Card hoverable bordered onClick={() => navigate(`/course/${course.code.replace(/\s/g, "").toLowerCase()}`)} style={{ borderRadius: "10px", boxShadow: "2px 2px 10px rgba(0, 0, 0, 0.1)" }}>
                  <Title level={5}>
                    <BookOutlined style={{ marginRight: "10px" }} />
                    {course.code} - {course.name}
                  </Title>
                  <Paragraph>
                    <strong>Credits:</strong> {course.credits} <br />
                    <strong>Recommended Semester:</strong> {course.semester}
                  </Paragraph>
                  {(course.prerequisites || []).length > 0 ? (
                    <div>
                      <strong>Prerequisites:</strong>{" "}
                      {course.prerequisites.map((prereq, i) => (
                        <Tag color="blue" key={i}>{prereq}</Tag>
                      ))}
                    </div>
                  ) : (
                    <Tag color="gray">No prerequisites</Tag>
                  )}
                </Card>
              </Col>
            ))}
          </Row>

          <Divider />

          <Title level={4} style={{ textAlign: "center", marginTop: "40px" }}>Prerequisite Flowchart</Title>
          <div style={{ backgroundColor: "#f0f2f5", padding: "20px", borderRadius: "8px", textAlign: "center" }}>
            <Tree treeData={generateTreeData(departmentCourses[selectedDepartment])} defaultExpandAll showLine switcherIcon={<DownOutlined />} />
          </div>
        </Content>
      </Layout>
    </Layout>
  );
};

export default CommonCore;
