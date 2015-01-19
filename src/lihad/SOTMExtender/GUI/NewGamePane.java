package lihad.SOTMExtender.GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import lihad.SOTMExtender.Extender;
import lihad.SOTMExtender.Objects.DifficultyType;
import lihad.SOTMExtender.Objects.Game;
import lihad.SOTMExtender.Objects.Player;

public class NewGamePane extends JPanel implements ActionListener{

	private static final long serialVersionUID = 1004803986986016004L;

	private Set<JComboBox<Player>> player_combos;
	private JComboBox<DifficultyType> difficulty_combo;
	private JPanel combo_pane, grid_pane;

	NewGamePane(){
		super(new BorderLayout());

		grid_pane = new JPanel();
		grid_pane.setLayout(new BoxLayout(grid_pane, BoxLayout.Y_AXIS));

		setPreferredSize(new Dimension(200,200));
		setSize(new Dimension(200,200));

		JPanel selection_pane = new JPanel();

		player_combos = new HashSet<JComboBox<Player>>();

		JRadioButton two = new JRadioButton("2");
		two.setMnemonic(KeyEvent.VK_2);
		two.setActionCommand("2");

		JRadioButton three = new JRadioButton("3");
		three.setMnemonic(KeyEvent.VK_3);
		three.setActionCommand("3");

		JRadioButton four = new JRadioButton("4");
		four.setMnemonic(KeyEvent.VK_4);
		four.setActionCommand("4");
		four.setSelected(true);

		JRadioButton five = new JRadioButton("5");
		five.setMnemonic(KeyEvent.VK_5);
		five.setActionCommand("5");

		//Group the radio buttons.
		ButtonGroup group = new ButtonGroup();
		group.add(two);
		group.add(three);
		group.add(four);
		group.add(five);

		selection_pane.add(two);
		selection_pane.add(three);
		selection_pane.add(four);
		selection_pane.add(five);

		//Register a listener for the radio buttons.
		two.addActionListener(this);
		three.addActionListener(this);
		four.addActionListener(this);
		five.addActionListener(this);

		grid_pane.add(selection_pane);

		combo_pane = loadComboPanel(4);

		difficulty_combo = new JComboBox<DifficultyType>();
		difficulty_combo.addItem(DifficultyType.EXTREMELY_EASY);
		difficulty_combo.addItem(DifficultyType.VERY_EASY);
		difficulty_combo.addItem(DifficultyType.EASY);
		difficulty_combo.addItem(DifficultyType.TYPICAL);
		difficulty_combo.addItem(DifficultyType.DIFFICULT);
		difficulty_combo.addItem(DifficultyType.VERY_HARD);
		difficulty_combo.addItem(DifficultyType.EXTREMELY_HARD);
		difficulty_combo.addItem(DifficultyType.IMPOSSIBLE);

		grid_pane.add(difficulty_combo);

		grid_pane.add(combo_pane);

		this.add(grid_pane, BorderLayout.NORTH);

		JButton accept = new JButton("accept");

		accept.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				Set<Player> players = new HashSet<Player>();
				for(JComboBox<Player> cb : player_combos) players.add(cb.getItemAt(cb.getSelectedIndex()));
				try{
					Game game = new Game(players, difficulty_combo.getItemAt(difficulty_combo.getSelectedIndex()));
					Extender.addGame(game);
					Extender.saveGameData(game);
				}catch(IllegalArgumentException e){
					Extender.getLogger().warning(Game.class, "Bad hero set.  Game creation Cancelled.  Try Again.");
				}
			}
		});
		this.add(accept, BorderLayout.SOUTH);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		player_combos.clear();
		grid_pane.remove(combo_pane);
		combo_pane = loadComboPanel(Integer.parseInt(e.getActionCommand()));
		grid_pane.add(combo_pane);
		Extender.getGUI().pack();
		Extender.getGUI().repaint();
	}

	public JPanel loadComboPanel(int count){
		JPanel pane = new JPanel(new GridLayout(count, 2));

		for(int i = 0; i<count; i++){
			JComboBox<Player> player_combo = new JComboBox<Player>();
			player_combo.setRenderer(new PlayerComboRender());
			for(Player player : Extender.getPlayers())player_combo.addItem(player);
			pane.add(player_combo);
			player_combos.add(player_combo);
		}

		return pane;

	}
}
