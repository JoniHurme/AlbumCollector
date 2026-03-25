import axios from 'axios';

const API_BASE_URL = '/api/records';

export const getAllRecords = () => axios.get(API_BASE_URL);
export const addRecord = (record) => axios.post(API_BASE_URL, record);
export const deleteRecord = (id) => axios.delete(`${API_BASE_URL}/${id}`);
