import React from 'react';
import { Routes, Route, BrowserRouter } from 'react-router-dom';
import Main from './components/Main';
import CoursePage from './components/CoursePage';
import LoginPage from './components/LoginPage';
import SignUpPage from './components/SignUpPage';
import RateReviewPage from "./components/RateReviewPage";
import ExploreSyllabusPage from "./components/ExploreSyllabusPage";
import DepartmentPage from "./components/DepartmentPage";
import AboutPage from "./components/AboutPage";
import CommonCore from './components/CommonCore';


const NotFound = () => <h2>Page Not Found</h2>;

const App = () => {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Main />} />
        <Route path="/login" element={<LoginPage />} />
        <Route path="/signup" element={<SignUpPage />} />
        <Route path="/course/:id" element={<CoursePage />} />
        <Route path="/rate-review" element={<RateReviewPage />} />
        <Route path="/explore-syllabus" element={<ExploreSyllabusPage />} />
        <Route path="/department/:departmentName" element={<DepartmentPage />} />
        <Route path="/about" element={<AboutPage />} />
        <Route path="/common-core" element={<CommonCore />} />   {/*new*/}
        <Route path="*" element={<NotFound />} />
      </Routes>
    </BrowserRouter>
  );
};

export default App;
