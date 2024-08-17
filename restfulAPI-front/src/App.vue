<template>
  <div>
    <div id="menu">
      <VueDatePicker v-model="startDateTime" plcaeholder="Start DateTime"/>
      <VueDatePicker v-model="endDateTime" plcaeholder="End DateTime"/>
      <button @click="getlist">시간대별 전체항목 조회</button>
      <button @click="getlist">시간대별 가입자수 조회</button>
      <button @click="getlist">시간대별 탈퇴자수 조회</button>
      <button @click="getlist">시간대별 결제금액 조회</button>
      <button @click="getlist">시간대별 사용금액 조회</button>
      <button @click="getlist">시간대별 매출금액 조회</button>
      <button @click="goToRegister">등록</button>
    </div>
    <router-view :records="records"/>
  </div>
</template>

<script>
import { getlist } from './api/recordApi';
import VueDatePicker from '@vuepic/vue-datepicker';
import '@vuepic/vue-datepicker/dist/main.css';

export default {
  name: 'App',
  components:{
    VueDatePicker
  },
  data() {
    return {
      startDateTime: '',
      endDateTime: '',
      records: []
    };
  },
  methods: {
    async getlist() {
      if(!this.startDateTime || !this.endDateTime){
        alert('시작 날짜와 시간을 모두 선택해주세요.');
        return;
      }

      try {
        console.log('Start Date: ', this.startDateTime);
        console.log('End Date: ', this.endDateTime);
        this.records = await getlist(this.startDateTime, this.endDateTime);
        this.$router.push({ name: 'lists' });
      } catch (error) {
        console.error('Error get lists:', error);
      }
    },
    goToRegister() {
      this.$router.push({ name: 'Register' });
    }
  }
};
</script>
