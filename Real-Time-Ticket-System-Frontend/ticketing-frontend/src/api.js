import axios from 'axios';

const API_URL = 'http://localhost:8080/api';

export const configureSystem = async (config) => {
  await axios.post(`${API_URL}/configure`, config);
};

export const startSystem = async () => {
  await axios.post(`${API_URL}/start`);
};

export const stopSystem = async () => {
  await axios.post(`${API_URL}/stop`);
};

export const getLogs = async () => {
  const response = await axios.get(`${API_URL}/logs`);
  return response.data;
};

export const getTicketCount = async () => {
  const response = await axios.get(`${API_URL}/ticket-count`);
  return response.data;
};


export const deleteLogs = async () => {
  await axios.delete(`${API_URL}/logs`);
};
