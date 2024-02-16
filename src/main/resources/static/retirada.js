// Função para abrir ou fechar a sidebar ao clicar em "Stock-Control"
document.querySelector('.Logo').addEventListener('click', function() {
    document.querySelector('.sidebar').classList.toggle('open');
});

// Função para fechar a sidebar ao clicar fora dela
window.addEventListener('click', function(event) {
    var sidebar = document.querySelector('.sidebar');
    if (!event.target.matches('.Logo') && sidebar.classList.contains('open')) {
        sidebar.classList.remove('open');
    }
});
