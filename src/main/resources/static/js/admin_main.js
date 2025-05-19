var url = "/api/admin/management";

$(() => {
  $.ajax({
    url: `${url}/users/total`,
    method: "GET",
    success: (data) => {
        let totalUsers = document.getElementById("totalUsers");
        totalUsers.innerText = data;
    },
    error: () => {
      alert("로그인이 필요합니다.");
      document.location.href = "/admin/page/login";
    },
  });
  $.ajax({
    url: `${url}/payments/total`,
    method: "GET",
    success: (data) => {
        let totalPayments = document.getElementById("totalPayments");
        totalPayments.innerText = data;
    },
    error: () => {
      alert("로그인이 필요합니다.");
      document.location.href = "/admin/page/login";
    },
  });
  $.ajax({
    url: `${url}/posts/total`,
    method: "GET",
    success: (data) => {
        let totalUsers = document.getElementById("totalPosts");
        totalUsers.innerText = data;
    },
    error: () => {
      alert("로그인이 필요합니다.");
      document.location.href = "/admin/page/login";
    },
  });
});

