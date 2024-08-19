<template>
  <div>
    <div id="menu">
      <VueDatePicker v-model="startDateTime" plcaeholder="Start DateTime"/>
      <VueDatePicker v-model="endDateTime" plcaeholder="End DateTime"/>
      <v-btn @click="getlist">시간대별 전체항목 조회</v-btn>
      <v-btn @click="getSubscriber">시간대별 가입자수 조회</v-btn>
      <v-btn @click="getDropout">시간대별 탈퇴자수 조회</v-btn>
      <v-btn @click="getPaymentAmount">시간대별 결제금액 조회</v-btn>
      <v-btn @click="getAmountUsed">시간대별 사용금액 조회</v-btn>
      <v-btn @click="getSalesAmount">시간대별 매출금액 조회</v-btn>
    </div>
    <router-view :records="records"/>
  </div>
</template>

<script>
import { getAmountUsed, getDropouts, getlist, getPaymentAmount, getSalesAmount, getSubscribers } from './api/recordApi';
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
    async getSubscriber(){
      if(!this.startDateTime || !this.endDateTime){
        alert('시작 날짜와 시간을 모두 선택해주세요.');
        return;
      }
      try{
        this.records = await getSubscribers(this.startDateTime, this.endDateTime);
        this.$router.push({ name: 'subscribers' });
      }catch(error){
        console.error('Error get subscribers: ', error);
      }
    },
    async getDropout(){
      if(!this.startDateTime || !this.endDateTime){
        alert('시작 날짜와 시간을 모두 선택해주세요.');
        return;
      }
      try{
        this.records = await getDropouts(this.startDateTime, this.endDateTime);
        this.$router.push({ name: 'dropouts' });
      }catch(error){
        console.error('Error get dropouts: ', error);
      }
    },
    async getPaymentAmount(){
      if(!this.startDateTime || !this.endDateTime){
        alert('시작 날짜와 시간을 모두 선택해주세요.');
        return;
      }
      try{
        this.records = await getPaymentAmount(this.startDateTime, this.endDateTime);
        this.$router.push({ name: 'paymentAmount' });
      }catch(error){
        console.error('Error get paymentamount: ', error);
      }
    },
    async getAmountUsed(){
      if(!this.startDateTime || !this.endDateTime){
        alert('시작 날짜와 시간을 모두 선택해주세요.');
        return;
      }
      try{
        this.records = await getAmountUsed(this.startDateTime, this.endDateTime);
        this.$router.push({ name: 'amountUsed' });
      }catch(error){
        console.error('Error get amountused: ', error);
      }
    },
    async getSalesAmount(){
      if(!this.startDateTime || !this.endDateTime){
        alert('시작 날짜와 시간을 모두 선택해주세요.');
        return;
      }
      try{
        this.records = await getSalesAmount(this.startDateTime, this.endDateTime);
        this.$router.push({ name: 'salesAmount' });
      }catch(error){
        console.error('Error get salesamout: ', error);
      }
    }
  }
};
</script>