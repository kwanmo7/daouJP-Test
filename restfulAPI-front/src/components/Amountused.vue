<template>
  <v-container>
    <h1>시간대별 사용금액 조회</h1>
    <v-data-table
      :headers="headers"
      :items="records"
      class="elevation-1"
    >
    <template v-slot:item.formattedTime="{ item }">
        <v-text-field v-model="item.formattedTime" :disabled="!item.isEditing" label="시간"></v-text-field>
      </template>
      <template v-slot:item.amountUsed="{ item }">
        <v-text-field v-model="item.amountUsed" :disabled="!item.isEditing" label="사용금액" type="number"></v-text-field>
      </template>
    </v-data-table>
    <p v-if="records.length === 0">데이터가 없습니다.</p>
  </v-container>
</template>

<script>
import { reactive, toRefs } from 'vue';
import { VDataTable } from 'vuetify/components';

export default {
  name: 'amountUsed',
  components: {
    VDataTable
  },
  props: {
    records: {
      type: Array,
      required: true
    }
  },
  setup(){
    const state = reactive({
      headers: [
        { text: 'Time', value: 'formattedTime' },
        { text: 'amountUsed', value: 'amountUsed' },
      ]
    });

    return{
      ...toRefs(state)
    }
  }
}
</script>
