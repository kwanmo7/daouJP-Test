import axios from "axios";
import { formatISO } from 'date-fns';

export const getlist = async (start, end) => {
  try{
    const response = await axios.get('http://localhost:8080/api/list',{
      params: {
        start: start,
        end: end
      }
    });
    return response.data
  }catch(error){
    console.error('Error', error);
    throw error;
  }
};

export const updateRecord = async(record) =>{
  try{
    record.time = formatISO(new Date(record.time));
    console.log(record);
    const response = await axios.put('http://localhost:8080/api', record);
    return response.data;
  }catch(error){
    console.error('Error', error);
    throw error;
  }
}

export const deleteRecord = async(id) =>{
  try{
    await axios.delete('http://localhost:8080/api/${id}');
  }catch(error){
    console.error('Error', error);
    throw error;
  }
}

export const registerRecord = async() => {

};