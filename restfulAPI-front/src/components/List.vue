<template>
  <v-container>
    <h1>시간대별 전체 항목 조회</h1>
    <v-data-table
      :headers="headers"
      :items="records"
      class="elevation-1"
    >
      <template v-slot:item.formattedTime="{ item }">
        <v-text-field v-model="item.formattedTime" :disabled="!item.isEditing" label="Time"></v-text-field>
      </template>
      <template v-slot:item.subscribers="{ item }">
        <v-text-field v-model="item.subscribers" :disabled="!item.isEditing" label="Subscribers" type="number"></v-text-field>
      </template>
      <template v-slot:item.dropouts="{ item }">
        <v-text-field v-model="item.dropouts" :disabled="!item.isEditing" label="Dropouts" type="number"></v-text-field>
      </template>
      <template v-slot:item.paymentAmount="{ item }">
        <v-text-field v-model="item.paymentAmount" :disabled="!item.isEditing" label="Payment Amount" type="number"></v-text-field>
      </template>
      <template v-slot:item.amountUsed="{ item }">
        <v-text-field v-model="item.amountUsed" :disabled="!item.isEditing" label="Amount Used" type="number"></v-text-field>
      </template>
      <template v-slot:item.salesAmount="{ item }">
        <v-text-field v-model="item.salesAmount" :disabled="!item.isEditing" label="Sales Amount" type="number"></v-text-field>
      </template>
      <template v-slot:item.actions="{ item }">
        <v-btn v-if="!item.isEditing" color="primary" @click="editRecord(item)">수정</v-btn>
        <v-btn v-else color="blue darken-1" @click="saveEdit(item)">저장</v-btn>
        <v-btn color="red" @click="deleteRecord(item.id)">삭제</v-btn>
      </template>
    </v-data-table>
    <p v-if="records.length === 0">데이터가 없습니다.</p>
  </v-container>
</template>

<script>
import { reactive } from 'vue';
import { VDataTable } from 'vuetify/components';
import { updateRecord, deleteRecord } from '../api/recordApi';

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
  setup() {
    const state = reactive({
      headers: [
        { text: 'Time', value: 'formattedTime' },
        { text: 'Subscribers', value: 'subscribers' },
        { text: 'Dropouts', value: 'dropouts' },
        { text: 'Payment Amount', value: 'paymentAmount' },
        { text: 'Amount Used', value: 'amountUsed' },
        { text: 'Sales Amount', value: 'salesAmount' },
        { text: 'Actions', value: 'actions', sortable: false }
      ]
    });

    const editRecord = (record) => {
      record.isEditing = true;
    };

    const saveEdit = async (record) => {
      try {
        console.log(record);
        await updateRecord(record);
        record.isEditing = false;
      } catch (error) {
        console.error('Error updating record:', error);
      }
    };

    const deleteRecord = async (id) => {
      try {
        await deleteRecord(id);
        state.records = state.records.filter(record => record.id !== id);
      } catch (error) {
        console.error('Error deleting record:', error);
      }
    };

    return {
      ...state,
      editRecord,
      saveEdit,
      deleteRecord
    };
  }
};
</script>
