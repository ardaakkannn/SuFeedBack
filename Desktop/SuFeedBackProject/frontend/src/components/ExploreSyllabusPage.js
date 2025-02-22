import React from 'react';
import { useNavigate } from 'react-router-dom';
import {
  Layout,
  Typography,
  Button,
  Card,
  Table,
  Row,
  Col,
} from 'antd';
import { FileTextOutlined } from '@ant-design/icons';
import mockData from '../mockData';

const { Title } = Typography;
const { Content } = Layout;

const ExploreSyllabusPage = () => {
  const navigate = useNavigate();

  // Define syllabus data
  const syllabusData = mockData
    .filter((course) => course.syllabusLink) // Only courses with syllabus links
    .map((course, index) => ({
      key: index,
      course: course.course,
      instructor: course.instructor || 'Unknown',
      syllabusLink: course.syllabusLink,
    }));

  // Table columns
  const columns = [
    {
      title: 'Course',
      dataIndex: 'course',
      key: 'course',
      sorter: (a, b) => a.course.localeCompare(b.course),
    },
    {
      title: 'Instructor',
      dataIndex: 'instructor',
      key: 'instructor',
      sorter: (a, b) => a.instructor.localeCompare(b.instructor),
    },
    {
      title: 'Syllabus',
      dataIndex: 'syllabusLink',
      key: 'syllabusLink',
      render: (link) => (
        <Button
          type="link"
          icon={<FileTextOutlined />}
          href={link}
          target="_blank"
          rel="noopener noreferrer"
        >
          View Syllabus
        </Button>
      ),
    },
  ];

  return (
    <Layout style={{ minHeight: '100vh' }}>
      <Content style={{ padding: '50px' }}>
        <Row justify="center">
          <Col xs={24} sm={20} md={16} lg={12}>
            <Card>
              <Title level={2} style={{ textAlign: 'center' }}>
                Explore Syllabus Links
              </Title>
              <Table
                columns={columns}
                dataSource={syllabusData}
                pagination={{ pageSize: 5 }}
                rowKey="key"
              />
              <Button
                type="primary"
                style={{ marginTop: '20px' }}
                onClick={() => navigate('/')}
                block
              >
                Back to Home
              </Button>
            </Card>
          </Col>
        </Row>
      </Content>
    </Layout>
  );
};

export default ExploreSyllabusPage;
