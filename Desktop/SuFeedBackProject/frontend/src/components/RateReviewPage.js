import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { Form, Select, Input, Button, Rate, Typography, message, Card } from "antd";
import mockData from "../mockData";

const { Title } = Typography;
const { TextArea } = Input;
const { Option } = Select;

const RateReviewPage = () => {
  const navigate = useNavigate();
  const [form] = Form.useForm();
  const [submitting, setSubmitting] = useState(false);

  // Extract unique course names
  const courseOptions = mockData.map(course => course.course).filter((v, i, a) => a.indexOf(v) === i);

  // Submit handler
  const handleSubmit = (values) => {
    setSubmitting(true);
    setTimeout(() => {
      console.log("New Review Submitted:", values);
      message.success("Review submitted successfully!");
      form.resetFields();
      setSubmitting(false);
      navigate("/"); // Redirect to home page after submitting
    }, 1500);
  };

  return (
    <div style={{ maxWidth: "700px", margin: "50px auto", padding: "20px" }}>
      <Card>
        <Title level={2} style={{ textAlign: "center" }}>Rate & Review a Course</Title>
        
        <Form form={form} layout="vertical" onFinish={handleSubmit}>
          {/* Course Selection */}
          <Form.Item
            name="course"
            label="Select Course"
            rules={[{ required: true, message: "Please select a course" }]}
          >
            <Select placeholder="Choose a course">
              {courseOptions.map((course, index) => (
                <Option key={index} value={course}>{course}</Option>
              ))}
            </Select>
          </Form.Item>

          {/* Ratings */}
          <Form.Item name="contentRating" label="Content Rating">
            <Rate />
          </Form.Item>
          <Form.Item name="teachingRating" label="Teaching Rating">
            <Rate />
          </Form.Item>
          <Form.Item name="gradingRating" label="Grading Rating">
            <Rate />
          </Form.Item>
          <Form.Item name="workloadRating" label="Workload Rating">
            <Rate />
          </Form.Item>

          {/* Review Text */}
          <Form.Item
            name="reviewText"
            label="Write Your Review"
            rules={[{ required: true, message: "Please enter your review" }]}
          >
            <TextArea rows={4} placeholder="Write your review here..." />
          </Form.Item>

          {/* Submit Button */}
          <Form.Item>
            <Button type="primary" htmlType="submit" block loading={submitting}>
              Submit Review
            </Button>
          </Form.Item>
        </Form>
      </Card>
    </div>
  );
};

export default RateReviewPage;
