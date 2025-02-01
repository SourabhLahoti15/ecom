// notification.js
function showNotification(success, message, type) {
    const notification = document.getElementById('notification');
    notification.textContent = message;
    if (type === 'warning') {
        notification.style.backgroundColor = '#ff9800'; 
    } else if (type === 'error') {
        notification.style.backgroundColor = '#f44336'; 
    } else {
        notification.style.backgroundColor = '#4CAF50'; 
    }
    notification.style.opacity = 1;        
    
    setTimeout(() => {
        notification.style.opacity = 0;
    }, 3000);
}
