import React from 'react';
import ReactDOM from 'react-dom/client';
import { ConfigProvider } from 'antd';
import App from './App';

const root = ReactDOM.createRoot(document.getElementById('root'));

root.render(
  <ConfigProvider>
    <App />
  </ConfigProvider>
);
