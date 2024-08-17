<template>
   <div>
    <h1>Register Record</h1>
    <form @submit.prevent="registerRecord">
      <!-- 필요한 입력 필드 추가 -->
      <button type="submit">등록</button>
    </form>
  </div>
</template>
<script>
import { registerRecord } from '@/api/recordApi';

export default {
  name: 'Register',
  methods: {
    async registerRecord() {
      try {
        const newRecord = {
          // 필요한 데이터 필드 추가
        };
        await registerRecord(newRecord);
        alert('등록되었습니다.');
        this.$router.push('/records');
      } catch (error) {
        console.error('Error registering record:', error);
      }
    }
  }
};
</script>