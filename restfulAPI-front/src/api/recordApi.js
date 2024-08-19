import axios from "axios";

const apiKey = import.meta.env.apiKey;

const instance = axios.create({
  baseURL: 'http://localhost:8080/api',
  headers:{
    'API-Key': apiKey
  }
});

export const getlist = async (start, end) => {
  try{
    const response = await instance.get('http://localhost:8080/api/list',{
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
    const response = await instance.put('http://localhost:8080/api/update', record);
    return response.data;
  }catch(error){
    console.error('Error', error);
    throw error;
  }
}

export const deleteRecord = async(id) =>{
  try{
    console.log(typeof(id));
    await instance.delete(`http://localhost:8080/api/${id}`);
  }catch(error){
    console.error('Error', error);
    throw error;
  }
}

export const insertRecord = async(record) => {
  try{
    console.log("1111");
    const response = await instance.post(`http://localhost:8080/api`, record);
    return response;
  }catch(error){
    console.error('Error', error);
    throw error;
  }

};

export const getSubscribers = async(start, end) =>{
  try{
    const response = await instance.get('http://localhost:8080/api/subscriber',{
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
}

export const getDropouts = async(start, end) =>{
  try{
    const response = await instance.get('http://localhost:8080/api/dropouts',{
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
}

export const getPaymentAmount = async(start, end) =>{
  try{
    const response = await instance.get('http://localhost:8080/api/paymentAmount',{
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
}

export const getAmountUsed = async(start, end) =>{
  try{
    const response = await instance.get('http://localhost:8080/api/amountUsed',{
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
}

export const getSalesAmount = async(start, end) =>{
  try{
    const response = await instance.get('http://localhost:8080/api/salesAmount',{
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
}