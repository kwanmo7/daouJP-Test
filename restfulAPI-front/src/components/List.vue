<template>
  <v-container>
    <h1>시간대별 전체 항목 조회</h1>
    <v-data-table
      :headers="headers"
      :items="localRecords"
      class="elevation-1"
    >
      <template v-slot:item.formattedTime="{ item }">
        <v-text-field v-model="item.formattedTime" :disabled="!item.isEditing" label="시간"></v-text-field>
      </template>
      <template v-slot:item.subscribers="{ item }">
        <v-text-field v-model="item.subscribers" :disabled="!item.isEditing" label="가입자수" type="number"></v-text-field>
      </template>
      <template v-slot:item.dropouts="{ item }">
        <v-text-field v-model="item.dropouts" :disabled="!item.isEditing" label="탈퇴자수" type="number"></v-text-field>
      </template>
      <template v-slot:item.paymentAmount="{ item }">
        <v-text-field v-model="item.paymentAmount" :disabled="!item.isEditing" label="결제금액" type="number"></v-text-field>
      </template>
      <template v-slot:item.amountUsed="{ item }">
        <v-text-field v-model="item.amountUsed" :disabled="!item.isEditing" label="사용금액" type="number"></v-text-field>
      </template>
      <template v-slot:item.salesAmount="{ item }">
        <v-text-field v-model="item.salesAmount" :disabled="!item.isEditing" label="매출금액" type="number"></v-text-field>
      </template>
      <template v-slot:item.actions="{ item }">
        <v-btn v-if="!item.isEditing" color="primary" @click="editRecord(item)">수정</v-btn>
        <v-btn v-else color="blue darken-1" @click="saveEdit(item)">저장</v-btn>
        <v-btn color="red" @click="removeRecord(item.id)">삭제</v-btn>
        <v-btn v-if="item.isEditing" color="gray" @click="cancelEdit(item)">취소</v-btn>
      </template>
    </v-data-table>
    <br>
    <v-btn @click="showAddForm = true">새로 등록하기</v-btn>
    <v-dialog v-model="showAddForm" max-width="500px">
      <v-card>
        <v-card-title>
          <span class="headline">새 항목 등록</span>
        </v-card-title>
        <v-card-text>
          <v-form ref="form">
            <v-text-field v-model="newRecord.formattedTime" label="시간"></v-text-field>
            <v-text-field v-model="newRecord.subscribers" label="가입자수" type="number"></v-text-field>
            <v-text-field v-model="newRecord.dropouts" label="탈퇴자수" type="number"></v-text-field>
            <v-text-field v-model="newRecord.paymentAmount" label="결제금액" type="number"></v-text-field>
            <v-text-field v-model="newRecord.amountUsed" label="사용금액" type="number"></v-text-field>
            <v-text-field v-model="newRecord.salesAmount" label="매출금액" type="number"></v-text-field>
          </v-form>
        </v-card-text>
        <v-card-actions>
          <v-btn color="blue darken-1" text @click="showAddForm = false">취소</v-btn>
          <v-btn color="blue darken-1" text @click="addRecord">등록</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
    <p v-if="localRecords.length === 0">데이터가 없습니다.</p>
  </v-container>
</template>

<script>
import { reactive, toRefs, watch } from 'vue';
import { VDataTable } from 'vuetify/components';
import { updateRecord, deleteRecord , insertRecord } from '../api/recordApi';

export default {
  name: 'lists',
  components: {
    VDataTable
  },
  props: {
    records: {
      type: Array,
      required: true
    }
  },
  setup(props) {
    const state = reactive({
      headers: [
        { text: 'Time', value: 'formattedTime' },
        { text: 'Subscribers', value: 'subscribers' },
        { text: 'Dropouts', value: 'dropouts' },
        { text: 'Payment Amount', value: 'paymentAmount' },
        { text: 'Amount Used', value: 'amountUsed' },
        { text: 'Sales Amount', value: 'salesAmount' },
        { text: 'Actions', value: 'actions', sortable: false }
      ],
      localRecords: [...props.records],
      showAddForm: false,
      newRecord: {
        formattedTime: '',
        subscribers: 0,
        dropouts: 0,
        paymentAmount: 0,
        amountUsed: 0,
        salesAmount: 0
      }
    });
    
    watch(()=> props.records, (newRecords) =>{
      console.log("aaaaa");
      state.localRecords = [...newRecords];
    });

    const editRecord = (record) => {
      record.isEditing = true;
    };

    const cancelEdit = (record) => {
      record.isEditing = false;
    }

    const saveEdit = async (record) => {
      try {
        console.log(record);
        await updateRecord(record);
        record.isEditing = false;
      } catch (error) {
        if(error.response && error.response.status === 400){
          alert('값을 정확히 입력하세요.(시간: yyyy-MM-dd HH) ' + error.response.data.message);
        }else{
          console.error('Error updating record', error);
          alert('레코드 수정 중 오류 발생');
        }
      }
    };

    const removeRecord = async (id) => {
      try {
        console.log(id);
        await deleteRecord(id);
        alert("삭제되었습니다.");
        state.localRecords = state.localRecords.filter(record => record.id !== id);
      } catch (error) {
        console.error('Error delete record:', error);
      }
    };

    const addRecord = async() =>{
      try{
        const response = await insertRecord(state.newRecord);
        alert("등록하였습니다.");
        state.localRecords.push(response.data);
        state.showAddForm = false;
        state.newRecord = {
          formattedTime: '',
          subscribers: 0,
          dropouts: 0,
          paymentAmount: 0,
          amountUsed: 0,
          salesAmount: 0
        };
      }catch(error){
        if(error.response && error.response.status === 400){
          alert('값을 정확히 입력하세요.(시간: yyyy-MM-dd HH) ' + error.response.data.message);
        }else{
          console.error('Error Insert record', error);
          alert('레코드 추가 중 오류 발생');
        }
      }
    }

    return {
      ...toRefs(state),
      editRecord,
      saveEdit,
      cancelEdit,
      removeRecord,
      addRecord
    };
  }
};
</script>
