import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculadora extends JFrame {
    private JTextField display;
    private String operador;
    private double numero1, numero2, resultado;

    public Calculadora() {
        setTitle("Calculadora");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Display
        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.PLAIN, 40));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setBackground(Color.BLACK);
        display.setForeground(Color.WHITE);

        // Painel de botões
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4, 10, 10));  // 5 linhas e 4 colunas
        panel.setBackground(Color.GRAY);

        // Botões da calculadora
        String[] botoes = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", ".", "=", "+",
            "C", "+/-", "%", "√"
        };

        for (String texto : botoes) {
            JButton botao = new JButton(texto);
            botao.setFont(new Font("Arial", Font.PLAIN, 30));
            botao.setFocusPainted(false);
            botao.setBackground(Color.WHITE);
            botao.setForeground(Color.BLACK);
            botao.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    botaoPressionado(e);
                }
            });
            panel.add(botao);
        }

        // Layout principal
        setLayout(new BorderLayout(10, 10));
        add(display, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);

        setVisible(true);
    }

    private void botaoPressionado(ActionEvent e) {
        String comando = e.getActionCommand();

        // Se for um número ou ponto, exibe no display
        if ("0123456789.".contains(comando)) {
            display.setText(display.getText() + comando);
        }
        // Se for um operador, salva o número e o operador
        else if ("+-*/".contains(comando)) {
            operador = comando;
            numero1 = Double.parseDouble(display.getText());
            display.setText("");
        }
        // Se for o botão igual, faz o cálculo
        else if (comando.equals("=")) {
            numero2 = Double.parseDouble(display.getText());
            calcular();
            display.setText(String.valueOf(resultado));
        }
        // Se for o botão "C", limpa o display
        else if (comando.equals("C")) {
            display.setText("");
        }
        // Se for o botão "+/-", inverte o sinal
        else if (comando.equals("+/-")) {
            double valor = Double.parseDouble(display.getText());
            display.setText(String.valueOf(-valor));
        }
        // Se for o botão de porcentagem, faz o cálculo de porcentagem
        else if (comando.equals("%")) {
            double valor = Double.parseDouble(display.getText());
            display.setText(String.valueOf(valor / 100));
        }
        // Se for o botão "√", calcula a raiz quadrada
        else if (comando.equals("√")) {
            double valor = Double.parseDouble(display.getText());
            display.setText(String.valueOf(Math.sqrt(valor)));
        }
    }

    private void calcular() {
        switch (operador) {
            case "+":
                resultado = numero1 + numero2;
                break;
            case "-":
                resultado = numero1 - numero2;
                break;
            case "*":
                resultado = numero1 * numero2;
                break;
            case "/":
                if (numero2 != 0) {
                    resultado = numero1 / numero2;
                } else {
                    JOptionPane.showMessageDialog(this, "Não é possível dividir por zero!");
                    resultado = 0;
                }
                break;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Calculadora();
            }
        });
    }
}
