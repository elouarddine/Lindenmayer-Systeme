
package view;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;

import org.joml.Vector3f;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLJPanel;
import com.jogamp.opengl.glu.GLU;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import controle.Controleur;
import controle.Mode;

/**
 * La classe ConfigurationPanel sert à configurer et afficher les dessins 2D et 3D la même chose pour les modes Console et Graphique
 * en fonction du type d'affichage sélectionné. Elle facilite la navigation entre différentes configurations
 * et modes d'exemple à travers une interface utilisateur interactive.
 */
public class ConfigurationPanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JPanel configPanel , zoneDeDessin ,zoneDuDessin2D , navigationPanel , exemplePanel , rightPanel;
	private JSplitPane splitPane; 
	private boolean dessiner ; 
	private Controleur controleur;
	private GLJPanel zoneDuDessin3D;
	private float angleY = 0.0f;
	
	private float positionPlanteZ = -10.0f; 
	private int lastX = 0 ;
	private float zoomFactor = -295.0f; 
	private TypeAffichage typeAffichage ; 
	
	/**
	 * Initialise le panel de configuration avec le contrôleur et le type d'affichage spécifiés.
	 * Selon le mode de l'application (Console ou autre), différents panels sont préparés et affichés.
	 * 
	 * @param controleur Le contrôleur principal qui gère les interactions entre la vue et le modèle.
	 * @param typeAffichage Le type d'affichage sélectionné (DEUX_D ou TROIS_D) pour la visualisation.
	 * 
	 * @requires controleur != null;
	 * @requires typeAffichage != null;
	 * @requires mode == Mode.GRAPHIQUE; 
	 * 
	 * @ensures this.controleur == controleur;
	 * @ensures this.typeAffichage == typeAffichage;
	 */
	public ConfigurationPanel(Controleur controleur, TypeAffichage typeAffichage , Mode mode) {
		
		controleur.setMode(Mode.CONFIGURATION);
		this.typeAffichage = typeAffichage; 
		this.controleur = controleur;
		this.zoneDeDessin = initialiserZoneDeDessin(); 
		this.setLayout(new BorderLayout());
		
		navigationPanel = new Panels(controleur ,  this);
		this.add(navigationPanel, BorderLayout.WEST);
		
	}
	
	
	
	
	/**
	 * Initialise le panel de configuration pour le mode console, configurant les zones de dessin et intégrant les interactions
     * avec le modèle. Permet une visualisation flexible des dessins en 2D ou 3D, enrichissant l'expérience utilisateur en mode console.
	 *
	 * @param controleur Le contrôleur utilisé pour la gestion des interactions entre la vue et le modèle.
	 * @param mode Le mode de fonctionnement de l'application, ici Mode.CONSOLE.
	 * @param typeAffichage Le type d'affichage pour le rendu des dessins (DEUX_D ou TROIS_D).
	 * 
	 * @requires controleur != null; 
	 * @requires mode == Mode.CONSOLE; 
	 * @requires typeAffichage != null;
	 * 
	 * @ensures this.controleur == controleur; 
	 * @ensures this.typeAffichage == typeAffichage; 
	 * @ensures splitPane != null; 
	 */
	 public ConfigurationPanel(Controleur controleur, Mode mode, TypeAffichage typeAffichage) {
	        this.typeAffichage = typeAffichage;
	        this.controleur = controleur;
	        setLayout(new BorderLayout());
	        
	        this.zoneDeDessin = initialiserZoneDeDessin(); 

	        Panels panel = new Panels(this.controleur, this, this.controleur.getAxiome(), this.controleur.getRegles(), this.controleur.getNombreIterations(), this.controleur.getAngle());
	        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, this.zoneDeDessin, panel);
	        splitPane.setDividerLocation(800);
	        add(splitPane, BorderLayout.CENTER);

	        SwingUtilities.invokeLater(() -> {
	            setDessiner(true, this.typeAffichage);
	        });
	    }


	
	
	
	
	
	
	/**
	 * Met à jour l'interface du panel de configuration pour afficher la section spécifiée,
	 * avec une configuration adaptée en fonction du mode sélectionné (par exemple, Mode.EXEMPLES ou Mode.CONFIGURATION).
	 * Cette méthode reconstruit l'interface utilisateur basée sur le mode et le nombre de règles spécifiés.
	 * 
	 * @param section La section du mode d'affichage à activer dans l'interface utilisateur.
	 * @param nombreDeRegle Le nombre de règles de production à configurer pour les L-systèmes, pertinent uniquement en mode CONFIGURATION.
	 * 
	 * @requires section != null;
	 * @requires nombreDeRegle >= 0;
	 * @ensures (section == Mode.EXEMPLES || section == Mode.CONFIGURATION) ==> splitPane != null;
	 */
	public void updateInterfaceForSection(Mode section, int nombreDeRegle) {
		this.removeAll(); 
		this.setLayout(new BorderLayout());
		int a = 1; 
		int b = 6 ; 
		rightPanel = new JPanel(new BorderLayout()); 
		rightPanel.add(this.zoneDeDessin, BorderLayout.CENTER); 
		
		if (Mode.EXEMPLES.equals(section)) {
			if(TypeAffichage.TROIS_D.equals(this.typeAffichage)) {
				a = 6;
				b=9;
			}
			exemplePanel = new Panels(this.controleur  ,this , this.typeAffichage , a , b ); 
			splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, rightPanel , exemplePanel);
			splitPane.setDividerLocation(650); 
			
		} else if (Mode.CONFIGURATION.equals(section)) {
			configPanel = new Panels(this.controleur, this, nombreDeRegle); 
			splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,rightPanel, configPanel);
			splitPane.setDividerLocation(650); 
			
		} else {
			splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, rightPanel , new JPanel()); 
			splitPane.setDividerLocation(650); 
			
		}
		
		splitPane.setOneTouchExpandable(true); 
		
		JSplitPane mainSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, navigationPanel, splitPane); 
		mainSplitPane.setOneTouchExpandable(true);
		mainSplitPane.setDividerLocation(400); 
		
		this.add(mainSplitPane, BorderLayout.CENTER);
		this.revalidate();
		this.repaint();
	}
	
	
	
	/**
	 * Active ou désactive la fonctionnalité de dessin dans la zone de dessin spécifique, 
	 * en fonction du type d'affichage sélectionné (2D ou 3D).
	 * 
	 * @param dessiner Booléen indiquant si le dessin est activé (true) ou non (false).
	 * @param typeAffichage Le type d'affichage pour lequel activer ou désactiver le dessin.
	 * 
	 * @requires typeAffichage != null;
	 */
	public void setDessiner(boolean dessiner , TypeAffichage typeAffichage) {
		this.dessiner = dessiner;
		if(TypeAffichage.DEUX_D.equals(typeAffichage)) {
			zoneDuDessin2D.repaint();
		}
		if(TypeAffichage.TROIS_D.equals(typeAffichage)) {
			zoneDuDessin3D.repaint();
		}
	}
	
	
	/**
	 * Initialise la zone de dessin en fonction du type d'affichage sélectionné.
	 * Crée et retourne le panel approprié pour le dessin 2D ou la visualisation 3D.
	 * 
	 * @return Un JPanel pour les dessins 2D ou un GLJPanel pour les visualisations 3D.
	 * 
	 * @requires this.typeAffichage != null;
	 * @ensures (this.typeAffichage == TypeAffichage.DEUX_D) ==> (return instanceof JPanel);
	 * @ensures (this.typeAffichage == TypeAffichage.TROIS_D) ==> (return instanceof GLJPanel);
	 * @throws IllegalArgumentException si le type d'affichage n'est ni 2D ni 3D.
	 */
	public JPanel initialiserZoneDeDessin() {
		if (TypeAffichage.DEUX_D.equals(this.typeAffichage)) {
			initialiseZoneDeDessin2D();
			return zoneDuDessin2D;
		} else if (TypeAffichage.TROIS_D.equals(this.typeAffichage)) {
			initialiseZoneDeDessin3D();
			return zoneDuDessin3D;
		} else {
			
			throw new IllegalArgumentException("Type d'affichage non supporté: " + this.typeAffichage);
		}
	}
	
	/**
	 * Initialise le panel pour le dessin 2D. Configure le comportement de dessin et le style du panel.
	 * Ce panel est utilisé pour afficher les dessins 2D générés par l'utilisateur ou des exemples.
	 * 
	 * @requires this.zoneDuDessin2D == null;
	 * @ensures this.zoneDuDessin2D != null;
	 * @ensures this.zoneDuDessin2D.getBorder() != null;
	 * @ensures this.zoneDuDessin2D.getPreferredSize().equals(new Dimension(2000, 2000));
	 */
	private void initialiseZoneDeDessin2D() {
		
		zoneDuDessin2D = new JPanel() {
			private static final long serialVersionUID = 1L;
			
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				setBackground(Color.BLACK); 
				
				if (dessiner) {
					Graphics2D g2d = (Graphics2D) g;
					g2d.setStroke(new BasicStroke(1.5f));
					g2d.setColor(Color.green);
					int dx = getWidth() / 2; 
					int dy = getHeight();    
					controleur.afficherPlante2D(g2d, dx, dy);
				} 
			} 
		};
		
		zoneDuDessin2D.setPreferredSize(new Dimension(2000, 2000)); 
		zoneDuDessin2D.setBorder(BorderFactory.createLineBorder(Color.gray, 5)); 
		
	}
	
	
	/**
	 * Initialise le panel pour la visualisation 3D à l'aide de JOGL. Configure le panel pour l'affichage
	 * des modèles 3D, tels que les plantes générées selon les paramètres de L-système.
	 * 
	 * @requires this.zoneDuDessin3D == null;
	 * @ensures this.zoneDuDessin3D != null;
	 * @ensures this.zoneDuDessin3D.getBorder() != null;
	 * @ensures this.zoneDuDessin3D.getPreferredSize().equals(new Dimension(2000, 2000));
	 */
	private void initialiseZoneDeDessin3D() {
		GLProfile profile = GLProfile.get(GLProfile.GL2);
		GLCapabilities capabilities = new GLCapabilities(profile);
		zoneDuDessin3D = new GLJPanel(capabilities);
		
		zoneDuDessin3D.setPreferredSize(new Dimension(2000, 2000));
		zoneDuDessin3D.setBorder(BorderFactory.createLineBorder(Color.gray, 5));
		
		zoneDuDessin3D.addGLEventListener(new GLEventListener() {
			private GLU glu = new GLU();
			
			@Override
			public void init(GLAutoDrawable drawable) {
				final GL2 gl = drawable.getGL().getGL2();
				gl.glEnable(GL2.GL_DEPTH_TEST);
			}
			
			@Override
			public void display(GLAutoDrawable drawable) {
				final GL2 gl = drawable.getGL().getGL2();
				
				if (!dessiner) {
					return;
				}
				
				gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);
				gl.glLoadIdentity();
				
				glu.gluLookAt(0, -zoomFactor, 0, 0, 0, 0, 0, 0, 1);
				
				gl.glRotatef(angleY, 0.0f, 0.0f, 1.0f);
				
				gl.glColor3f(0.0f, 1.0f, 0.0f); 
				gl.glTranslatef(0.0f, 0.0f, positionPlanteZ);
				controleur.afficherPlante3D(gl, new Vector3f(0.0f, 0.0f, positionPlanteZ));
			}
			
			@Override
			public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
				GL2 gl = drawable.getGL().getGL2();
				gl.glViewport(x, y, width, height);
				
				gl.glMatrixMode(GL2.GL_PROJECTION);
				gl.glLoadIdentity();
				glu.gluPerspective(60.0f, (float) width / height, 0.1f, 1000.0f);
				gl.glMatrixMode(GL2.GL_MODELVIEW);
				
				float proportionZ = -50.0f * (float)height / 1500; 
				positionPlanteZ = proportionZ; 
			}
			
			@Override
			public void dispose(GLAutoDrawable drawable) {}
		});
		
		addMouseListenersFor3DControl(zoneDuDessin3D);
	}
	
	
	
	/**
	 * Ajoute les écouteurs pour contrôler la visualisation 3D via la souris.
	 * Permet à l'utilisateur de manipuler l'angle de vue et le zoom sur la visualisation 3D.
	 * 
	 * @param panel Le panel 3D auquel ajouter les écouteurs de souris.
	 * 
	 * @requires panel != null;
	 * @ensures panel.getMouseListeners().length > 0;
	 * @ensures panel.getMouseMotionListeners().length > 0;
	 * @ensures panel.getMouseWheelListeners().length > 0;
	 */
	private void addMouseListenersFor3DControl(GLJPanel panel) {
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				lastX = e.getX();
			}
		});
		
		panel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getX();
				
				angleY += (x - lastX);
				lastX = x;
				
				panel.repaint();
			}
		});
		
		panel.addMouseWheelListener(e -> {
			zoomFactor += e.getWheelRotation();
			panel.repaint();
		});
	}
	
}
