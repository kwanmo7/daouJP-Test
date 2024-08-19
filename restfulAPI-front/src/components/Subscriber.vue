<template>
  <v-container>
    <h1>시간대별 가입자수 조회</h1>
    <v-data-table
      :headers="headers"
      :items="records"
      class="elevation-1"
    >
    <template v-slot:item.formattedTime="{ item }">
        <v-text-field v-model="item.formattedTime" :disabled="!item.isEditing" label="시간"></v-text-field>
      </template>
      <template v-slot:item.subscribers="{ item }">
        <v-text-field v-model="item.subscribers" :disabled="!item.isEditing" label="가입자수" type="number"></v-text-field>
      </template>
    </v-data-table>
    <p v-if="records.length === 0">데이터가 없습니다.</p>
  </v-container>
</template>

<script>
import { reactive, toRefs } from 'vue';
import { VDataTable } from 'vuetify/components';

export default {
  name: 'subscribers',
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
        { text: 'Subscribers', value: 'subscribers' },
      ]
    });

    return{
      ...toRefs(state)
    }
  }
}
</script>
