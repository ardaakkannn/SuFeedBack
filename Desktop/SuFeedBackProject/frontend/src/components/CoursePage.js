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
  const navigate = useNavigate(); // Initialize navigation function
  const courseName = id.toUpperCase();

  const courseData = mockData.find(
    (course) => course.course.toLowerCase() === id.toLowerCase()
  );

  return (
    <Layout style={{ padding: '24px', maxWidth: '1100px', margin: '0 auto' }}>
      <Content>
        <Title level={2}>{courseName} - Course Review</Title>

        {courseData ? (
          <>
            {/* Course Overview Section */}
            <Card bordered style={{ marginBottom: '20px' }}>
              <Title level={4}>
                <UserOutlined /> Instructor(s):{' '}
                {courseData.instructor || 'Not Available'}
              </Title>
              <Paragraph>
                <Space>
                  <FileTextOutlined />
                  <strong>Final Exam:</strong>{' '}
                  {courseData.finalExam ? (
                    <Tag icon={<CheckCircleOutlined />} color="success">
                      Included
                    </Tag>
                  ) : (
                    <Tag icon={<CloseCircleOutlined />} color="error">
                      Not Included
                    </Tag>
                  )}
                </Space>
              </Paragraph>
              <Paragraph>
                <Space>
                  <ReadOutlined />
                  <strong>Assignment:</strong>{' '}
                  {courseData.assignment ? (
                    <Tag icon={<CheckCircleOutlined />} color="success">
                      Required
                    </Tag>
                  ) : (
                    <Tag icon={<CloseCircleOutlined />} color="error">
                      Not Required
                    </Tag>
                  )}
                </Space>
              </Paragraph>
              <Paragraph>
                <Space>
                  <BookOutlined />
                  <strong>Reading Materials:</strong>{' '}
                  {courseData.readingMaterial ? (
                    <Tag icon={<CheckCircleOutlined />} color="success">
                      Provided
                    </Tag>
                  ) : (
                    <Tag icon={<CloseCircleOutlined />} color="error">
                      Not Provided
                    </Tag>
                  )}
                </Space>
              </Paragraph>
            </Card>

            {/* Ratings Section */}
            <Row gutter={[16, 16]} style={{ marginBottom: '20px' }}>
              <Col xs={24} sm={12} md={6}>
                <Card>
                  <Title level={5}>Content</Title>
                  <Progress
                    percent={courseData.contentRating * 20}
                    status="active"
                  />
                </Card>
              </Col>
              <Col xs={24} sm={12} md={6}>
                <Card>
                  <Title level={5}>Teaching</Title>
                  <Progress
                    percent={courseData.teachingRating * 20}
                    status="active"
                  />
                </Card>
              </Col>
              <Col xs={24} sm={12} md={6}>
                <Card>
                  <Title level={5}>Grading</Title>
                  <Progress
                    percent={courseData.gradingRating * 20}
                    status="active"
                  />
                </Card>
              </Col>
              <Col xs={24} sm={12} md={6}>
                <Card>
                  <Title level={5}>Workload</Title>
                  <Progress
                    percent={courseData.workloadRating * 20}
                    status="active"
                  />
                </Card>
              </Col>
            </Row>

            {/* Course Content Breakdown */}
            <Card bordered style={{ marginBottom: '20px' }}>
              <Title level={4}>Course Topics</Title>
              <List
                dataSource={courseData.contentTopics}
                renderItem={(topic) => <List.Item>{topic}</List.Item>}
                locale={{ emptyText: 'No topics listed.' }}
              />
            </Card>

            {/* Student Reviews */}
            <Title level={3}>Student Reviews</Title>
            <List
              grid={{ gutter: 16, column: 2 }}
              dataSource={courseData.reviews}
              renderItem={(review) => (
                <List.Item>
                  <Card bordered>
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

            {/* Write Review Button with Navigation */}
            <Button
              type="primary"
              style={{ marginTop: '20px' }}
              onClick={() => navigate(`/rate-review`)} // Navigate to RateReviewPage
            >
              Write Review
            </Button>
          </>
        ) : (
          <Paragraph>No data available for {courseName}.</Paragraph>
        )}
      </Content>
    </Layout>
  );
};

export default CoursePage;
