import axios from "axios";

const apiKey = import.meta.env.VITE_API_KEY;

const instance = axios.create({
  baseURL: 'http://localhost:8080/api',
  headers:{
    'API-Key': apiKey,
    'Content-Type': 'application/json'
  }
});

export const getlist = async (start, end) => {
  try{
    console.log('API-Key', apiKey);
    console.log('Instance Headers: ', instance.defaults.headers);
    const response = await instance.get('/list',{
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
    const response = await instance.put('/update', record);
    return response.data;
  }catch(error){
    console.error('Error', error);
    throw error;
  }
}

export const deleteRecord = async(id) =>{
  try{
    console.log(typeof(id));
    await instance.delete(`/${id}`);
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
    const response = await instance.get('/subscriber',{
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
    const response = await instance.get('/dropouts',{
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
    const response = await instance.get('/paymentAmount',{
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
    const response = await instance.get('/amountUsed',{
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
    const response = await instance.get('/salesAmount',{
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