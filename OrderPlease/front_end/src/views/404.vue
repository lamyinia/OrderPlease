<template>
  <div class="not-found-container">
    <div class="not-found-content">
      <!-- 404 数字动画 -->
      <div class="error-code">
        <span class="digit">4</span>
        <span class="digit">0</span>
        <span class="digit">4</span>
      </div>

      <!-- 中心插图 -->
      <div class="illustration">
        <div class="astronaut">

        </div>
      </div>

      <!-- 错误信息 -->
      <h1 class="title">页面飞走了</h1>
      <p class="description">
        您访问的页面可能已被移除、重命名或暂时不可用
      </p>

      <!-- 操作按钮 -->
      <div class="actions">
        <button @click="goHome" class="home-button">
          <span>返回首页</span>
        </button>
        <button @click="goBack" class="back-button">
          <span>返回上一页</span>
        </button>
      </div>

      <!-- 额外帮助 -->
      <div class="help-links">
        <p>需要帮助？</p>
        <div>
          <a href="#" @click.prevent="contactSupport">联系支持</a>
          <span class="divider">•</span>
          <a href="#" @click.prevent="reportProblem">报告问题</a>
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import { useRouter } from 'vue-router';

export default defineComponent({
  name: 'NotFoundPage',

  setup() {
    const router = useRouter();

    const goHome = () => {
      router.push('/');
    };

    const goBack = () => {
      if (window.history.length > 1) {
        router.go(-1);
      } else {
        router.push('/');
      }
    };

    const contactSupport = () => {
      // 实际应用中替换为真实的支持链接
      window.open('mailto:support@example.com');
    };

    const reportProblem = () => {
      // 实际应用中替换为真实的问题报告链接
      window.open('https://github.com/your-repo/issues');
    };

    return {
      goHome,
      goBack,
      contactSupport,
      reportProblem
    };
  }
});
</script>

<style lang="scss" scoped>
.not-found-container {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  background: linear-gradient(135deg, #f5f7fa 0%, #c3cfe2 100%);
  padding: 20px;
}

.not-found-content {
  text-align: center;
  max-width: 600px;
  padding: 40px;
  background: white;
  border-radius: 20px;
  box-shadow: 0 15px 35px rgba(50, 50, 93, 0.1), 0 5px 15px rgba(0, 0, 0, 0.07);
  position: relative;
  overflow: hidden;

  &::before {
    content: '';
    position: absolute;
    top: -50px;
    right: -50px;
    width: 200px;
    height: 200px;
    border-radius: 50%;
    background: rgba(26, 127, 237, 0.05);
  }

  &::after {
    content: '';
    position: absolute;
    bottom: -30px;
    left: -30px;
    width: 150px;
    height: 150px;
    border-radius: 50%;
    background: rgba(26, 127, 237, 0.05);
  }
}

.error-code {
  font-size: 8rem;
  font-weight: 900;
  color: #1a7fed;
  margin-bottom: 20px;
  position: relative;
  display: inline-block;

  .digit {
    display: inline-block;
    position: relative;
    animation: digitFloat 3s ease-in-out infinite;

    &:nth-child(1) { animation-delay: 0s; }
    &:nth-child(2) { animation-delay: 0.2s; }
    &:nth-child(3) { animation-delay: 0.4s; }
  }
}

@keyframes digitFloat {
  0%, 100% { transform: translateY(0); }
  50% { transform: translateY(-20px); }
}

.illustration {
  margin: 20px auto;
  width: 180px;
  height: 180px;
  position: relative;

  .astronaut {
    position: relative;
    width: 100%;
    height: 100%;

    .floating {
      animation: float 6s ease-in-out infinite;
    }
  }
}

@keyframes float {
  0%, 100% { transform: translateY(0) rotate(0deg); }
  25% { transform: translateY(-15px) rotate(5deg); }
  50% { transform: translateY(-10px) rotate(0deg); }
  75% { transform: translateY(-15px) rotate(-5deg); }
}

.title {
  font-size: 2.2rem;
  color: #2d3748;
  margin-bottom: 15px;
  animation: fadeInUp 0.8s ease-out forwards;
  opacity: 0;
  animation-delay: 0.3s;
}

.description {
  font-size: 1.2rem;
  color: #718096;
  margin-bottom: 30px;
  max-width: 500px;
  margin-left: auto;
  margin-right: auto;
  animation: fadeInUp 0.8s ease-out forwards;
  opacity: 0;
  animation-delay: 0.4s;
}

.actions {
  display: flex;
  justify-content: center;
  gap: 15px;
  margin-bottom: 30px;
  animation: fadeInUp 0.8s ease-out forwards;
  opacity: 0;
  animation-delay: 0.5s;

  button {
    padding: 12px 24px;
    border-radius: 50px;
    font-size: 1rem;
    font-weight: 600;
    cursor: pointer;
    transition: all 0.3s ease;
    border: none;
    outline: none;
    position: relative;
    overflow: hidden;

    span {
      position: relative;
      z-index: 2;
    }

    &::before {
      content: '';
      position: absolute;
      top: 0;
      left: 0;
      width: 100%;
      height: 100%;
      border-radius: 50px;
      transition: transform 0.3s ease;
      z-index: 1;
    }
  }

  .home-button {
    background: linear-gradient(135deg, #1a7fed 0%, #0d5cb6 100%);
    color: white;

    &::before {
      background: linear-gradient(135deg, #0d5cb6 0%, #1a7fed 100%);
      transform: scale(0);
    }

    &:hover::before {
      transform: scale(1);
    }
  }

  .back-button {
    background: white;
    color: #1a7fed;
    border: 2px solid #1a7fed;

    &:hover {
      background: #f0f7ff;
      transform: translateY(-3px);
    }
  }
}

.help-links {
  color: #718096;
  font-size: 0.95rem;
  animation: fadeInUp 0.8s ease-out forwards;
  opacity: 0;
  animation-delay: 0.6s;

  p {
    margin-bottom: 8px;
  }

  a {
    color: #1a7fed;
    text-decoration: none;
    transition: all 0.2s ease;

    &:hover {
      color: #0d5cb6;
      text-decoration: underline;
    }
  }

  .divider {
    margin: 0 10px;
    color: #cbd5e0;
  }
}

@keyframes fadeInUp {
  0% {
    opacity: 0;
    transform: translateY(20px);
  }
  100% {
    opacity: 1;
    transform: translateY(0);
  }
}

@media (max-width: 600px) {
  .error-code {
    font-size: 6rem;
  }

  .title {
    font-size: 1.8rem;
  }

  .description {
    font-size: 1rem;
  }

  .actions {
    flex-direction: column;
    align-items: center;
    gap: 10px;

    button {
      width: 100%;
      max-width: 280px;
    }
  }
}
</style>