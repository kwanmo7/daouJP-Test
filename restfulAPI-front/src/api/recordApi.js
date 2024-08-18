import axios from "axios";

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
    const response = await axios.put('http://localhost:8080/api/update', record);
    return response.data;
  }catch(error){
    console.error('Error', error);
    throw error;
  }
}

export const deleteRecord = async(id) =>{
  try{
    console.log(typeof(id));
    await axios.delete(`http://localhost:8080/api/${id}`);
  }catch(error){
    console.error('Error', error);
    throw error;
  }
}

export const insertRecord = async(record) => {
  try{
    console.log("1111");
    const response = await axios.post(`http://localhost:8080/api`, record);
    return response;
  }catch(error){
    console.error('Error', error);
    throw error;
  }
};