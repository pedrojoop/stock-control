document.getElementById('stock-control-link').addEventListener('click', function() {
    document.querySelector('.sidebar').classList.toggle('open');
});

// Função para criar um gráfico de barras
function createBarChart(data, labels, title, containerId) {
    var chartContainer = document.getElementById(containerId);

    // Cria o título do gráfico
    var chartTitle = document.createElement("h2");
    chartTitle.textContent = title;
    chartContainer.appendChild(chartTitle);

    // Cria a div para o gráfico de barras
    var chartDiv = document.createElement("div");
    chartDiv.classList.add("chart");
    chartContainer.appendChild(chartDiv);

    // Cria as barras do gráfico
    for (var i = 0; i < data.length; i++) {
        var bar = document.createElement("div");
        bar.classList.add("bar");
        bar.style.height = data[i] * 10 + "px"; // Ajusta a altura da barra
        bar.textContent = data[i]; // Adiciona o valor como texto na barra
        chartDiv.appendChild(bar);

        // Adiciona o rótulo do eixo X
        var label = document.createElement("div");
        label.classList.add("chart-label");
        label.textContent = labels[i];
        chartDiv.appendChild(label);
    }
}

// Dados de exemplo para os gráficos
var produtosPertoAcabarData = [5, 8, 3, 6, 4];
var produtosPertoAcabarLabels = ["Produto A", "Produto B", "Produto C", "Produto D", "Produto E"];
var valorRetiradoData = [100, 200, 150, 300, 250];
var valorRetiradoLabels = ["Produto A", "Produto B", "Produto C", "Produto D", "Produto E"];

// Chamada da função para criar os gráficos
createBarChart(produtosPertoAcabarData, produtosPertoAcabarLabels, "Produtos Perto de Acabar", "produtosPertoAcabarChart");
createBarChart(valorRetiradoData, valorRetiradoLabels, "Valor Retirado dos Produtos", "valorRetiradoChart");
