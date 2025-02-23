import React from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import {
  Layout,
  Typography,
  Button,
  Row,
  Col,
  Progress,
  List,
  Card,
  Tag,
  Space,
} from 'antd';
import {
  BookOutlined,
  UserOutlined,
  FileTextOutlined,
  ReadOutlined,
  CheckCircleOutlined,
  CloseCircleOutlined,
} from '@ant-design/icons';
import mockData from '../mockData';

const { Title, Paragraph } = Typography;
const { Content } = Layout;

const CoursePage = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const courseName = id.toUpperCase();

  const courseData = mockData.find(
    (course) => course.course.toLowerCase() === id.toLowerCase()
  );

  if (!courseData) {
    return (
      <Layout style={{ padding: '24px', maxWidth: '1100px', margin: '0 auto' }}>
        <Content>
          <Title level={2}>{courseName} - Course Review</Title>
          <Paragraph>No data available for {courseName}.</Paragraph>
        </Content>
      </Layout>
    );
  }

  return (
    <Layout style={{ padding: '24px', maxWidth: '1100px', margin: '0 auto' }}>
      <Content>
        {/* Header Section */}
        <Card
          bordered={false}
          style={{
            marginBottom: '20px',
            backgroundColor: '#f0f2f5',
            borderRadius: '8px',
          }}
        >
          <Title level={2} style={{ margin: 0 }}>
            {courseName} - Course Review
          </Title>
          <Paragraph style={{ marginTop: '8px', color: '#595959' }}>
            {courseData.description || 'No description available.'}
          </Paragraph>
        </Card>

        {/* Course Details */}
        <Card bordered style={{ marginBottom: '20px' }}>
          <Row gutter={[16, 16]}>
            <Col xs={24} sm={12} md={6}>
              <Space>
                <UserOutlined style={{ fontSize: '20px' }} />
                <div>
                  <Title level={5} style={{ margin: 0 }}>
                    Instructor(s)
                  </Title>
                  <Paragraph style={{ margin: 0 }}>
                    {courseData.instructor || 'Not Available'}
                  </Paragraph>
                </div>
              </Space>
            </Col>
            <Col xs={24} sm={12} md={6}>
              <Space>
                <FileTextOutlined style={{ fontSize: '20px' }} />
                <div>
                  <Title level={5} style={{ margin: 0 }}>
                    Final Exam
                  </Title>
                  <Tag
                    icon={
                      courseData.finalExam ? (
                        <CheckCircleOutlined />
                      ) : (
                        <CloseCircleOutlined />
                      )
                    }
                    color={courseData.finalExam ? 'success' : 'error'}
                    style={{ marginTop: '4px' }}
                  >
                    {courseData.finalExam ? 'Included' : 'Not Included'}
                  </Tag>
                </div>
              </Space>
            </Col>
            <Col xs={24} sm={12} md={6}>
              <Space>
                <ReadOutlined style={{ fontSize: '20px' }} />
                <div>
                  <Title level={5} style={{ margin: 0 }}>
                    Assignment
                  </Title>
                  <Tag
                    icon={
                      courseData.assignment ? (
                        <CheckCircleOutlined />
                      ) : (
                        <CloseCircleOutlined />
                      )
                    }
                    color={courseData.assignment ? 'success' : 'error'}
                    style={{ marginTop: '4px' }}
                  >
                    {courseData.assignment ? 'Required' : 'Not Required'}
                  </Tag>
                </div>
              </Space>
            </Col>
            <Col xs={24} sm={12} md={6}>
              <Space>
                <BookOutlined style={{ fontSize: '20px' }} />
                <div>
                  <Title level={5} style={{ margin: 0 }}>
                    Reading Materials
                  </Title>
                  <Tag
                    icon={
                      courseData.readingMaterial ? (
                        <CheckCircleOutlined />
                      ) : (
                        <CloseCircleOutlined />
                      )
                    }
                    color={courseData.readingMaterial ? 'success' : 'error'}
                    style={{ marginTop: '4px' }}
                  >
                    {courseData.readingMaterial ? 'Provided' : 'Not Provided'}
                  </Tag>
                </div>
              </Space>
            </Col>
          </Row>
        </Card>

        {/* Ratings Overview */}
        <Card bordered style={{ marginBottom: '20px' }}>
          <Title level={4}>Course Ratings</Title>
          <Row gutter={[16, 16]}>
            <Col xs={24} sm={12} md={6}>
              <Title level={5}>Content</Title>
              <Progress
                percent={courseData.contentRating * 20}
                status="active"
              />
            </Col>
            <Col xs={24} sm={12} md={6}>
              <Title level={5}>Teaching</Title>
              <Progress
                percent={courseData.teachingRating * 20}
                status="active"
              />
            </Col>
            <Col xs={24} sm={12} md={6}>
              <Title level={5}>Grading</Title>
              <Progress
                percent={courseData.gradingRating * 20}
                status="active"
              />
            </Col>
            <Col xs={24} sm={12} md={6}>
              <Title level={5}>Workload</Title>
              <Progress
                percent={courseData.workloadRating * 20}
                status="active"
              />
            </Col>
          </Row>
        </Card>

        {/* Course Topics */}
        <Card bordered style={{ marginBottom: '20px', backgroundColor: '#FAFAFA', borderRadius: '8px' }}>
          <Title level={4}>Course Topics</Title>
          <List
            dataSource={courseData.contentTopics}
            renderItem={(topic) => (
              <List.Item>
                <Tag color="blue">{topic}</Tag>
              </List.Item>
            )}
            locale={{ emptyText: 'No topics listed.' }}
          />
        </Card>

        {/* Student Reviews Section */}
        <Card bordered style={{ marginBottom: '20px', backgroundColor: '#FFFFFF', borderRadius: '8px' }}>
          <Title level={4}>Student Reviews</Title>
          <List
            grid={{ gutter: 16, column: 2 }}
            dataSource={courseData.reviews}
            renderItem={(review) => (
              <List.Item>
                <Card
                  bordered
                  style={{
                    backgroundColor: '#F8F9FA',
                    borderRadius: '8px',
                    boxShadow: '0 2px 8px rgba(0, 0, 0, 0.1)',
                  }}
                >
                  <Paragraph>
                    <strong>Content:</strong> {review.content}
                  </Paragraph>
                  <Paragraph>
                    <strong>Teaching:</strong> {review.teaching}
                  </Paragraph>
                  <Paragraph>
                    <strong>Grading:</strong> {review.grading}
                  </Paragraph>
                  <Paragraph>
                    <strong>Workload:</strong> {review.workload}
                  </Paragraph>
                </Card>
              </List.Item>
            )}
            locale={{ emptyText: `No reviews yet for ${courseName}.` }}
          />
        </Card>

        {/* Write Review Button */}
        <Button
          type="primary"
          style={{
            marginTop: '20px',
            backgroundColor: '#007BFF',
            borderRadius: '6px',
            padding: '10px 20px',
            fontSize: '16px',
            boxShadow: '0 2px 8px rgba(0, 0, 0, 0.2)',
          }}
          onClick={() => navigate(`/rate-review`)}
        >
          Write Review
        </Button>
      </Content>
    </Layout>
  );
};

export default CoursePage;
