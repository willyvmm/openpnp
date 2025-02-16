/*
 * Copyright (C) 2011 Jason von Nieda <jason@vonnieda.org>
 * 
 * This file is part of OpenPnP.
 * 
 * OpenPnP is free software: you can redistribute it and/or modify it under the terms of the GNU
 * General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * OpenPnP is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General
 * Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with OpenPnP. If not, see
 * <http://www.gnu.org/licenses/>.
 * 
 * For more information about OpenPnP visit http://openpnp.org
 */

package org.openpnp.machine.reference.wizards;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import org.openpnp.Translations;
import org.openpnp.gui.components.ComponentDecorators;
import org.openpnp.gui.support.AbstractConfigurationWizard;
import org.openpnp.gui.support.IntegerConverter;
import org.openpnp.machine.reference.ReferencePnpJobProcessor;
import org.openpnp.machine.reference.ReferencePnpJobProcessor.JobOrderHint;
import org.openpnp.spi.PnpJobPlanner.Strategy;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

@SuppressWarnings("serial")
public class ReferencePnpJobProcessorConfigurationWizard extends AbstractConfigurationWizard {
    private final ReferencePnpJobProcessor jobProcessor;
    private JComboBox<JobOrderHint> comboBoxJobOrder;
    private JComboBox<Strategy> comboBoxPlannerStrategy;
    private JTextField maxVisionRetriesTextField;
    private JCheckBox steppingToNextMotion;
    private JCheckBox optimizeMultipleNozzles;
    private JCheckBox preRotateAllNozzles;
    private JCheckBox useAsyncFeed;
    private JCheckBox feedAfterPick;
    private JCheckBox avoidConsecutivePicksFromSameFeeder;
    
    public ReferencePnpJobProcessorConfigurationWizard(ReferencePnpJobProcessor jobProcessor) {
        this.jobProcessor = jobProcessor;
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        JPanel panelGeneral = new JPanel();
        panelGeneral.setBorder(new TitledBorder(null, "General", TitledBorder.LEADING,
                TitledBorder.TOP, null, null));
        contentPanel.add(panelGeneral);
        panelGeneral.setLayout(new FormLayout(new ColumnSpec[] {
                FormSpecs.RELATED_GAP_COLSPEC,
                FormSpecs.DEFAULT_COLSPEC,
                FormSpecs.RELATED_GAP_COLSPEC,
                FormSpecs.DEFAULT_COLSPEC,},
                new RowSpec[] {
                        FormSpecs.RELATED_GAP_ROWSPEC,
                        FormSpecs.DEFAULT_ROWSPEC,
                        FormSpecs.RELATED_GAP_ROWSPEC,
                        FormSpecs.DEFAULT_ROWSPEC,
                        FormSpecs.RELATED_GAP_ROWSPEC,
                        FormSpecs.DEFAULT_ROWSPEC,
                        FormSpecs.RELATED_GAP_ROWSPEC,
                        FormSpecs.DEFAULT_ROWSPEC,
                        FormSpecs.RELATED_GAP_ROWSPEC,
                        FormSpecs.DEFAULT_ROWSPEC,
                        FormSpecs.RELATED_GAP_ROWSPEC,
                        FormSpecs.DEFAULT_ROWSPEC,}));

        // FIXME: this translation reference looks quite different to the one used below and shall be synchronized
        // !! if this translation reference is changed, change the one in ReferencePnPJobProcessor line 89 as well to keep both synchronized.
        JLabel lblJobOrder = new JLabel(Translations.getString("MachineSetup.JobProcessors.ReferencePnpJobProcessor.Label.JobOrder")); //$NON-NLS-1$
        lblJobOrder.setToolTipText(Translations.getString("MachineSetup.JobProcessors.ReferencePnpJobProcessor.Label.JobOrder.toolTipText")); //$NON-NLS-1$
        panelGeneral.add(lblJobOrder, "2, 2, right, default");

        comboBoxJobOrder = new JComboBox<JobOrderHint>(JobOrderHint.values());
        panelGeneral.add(comboBoxJobOrder, "4, 2");

        JLabel lblPlannerStrategy = new JLabel(Translations.getString("MachineSetup.JobProcessors.ReferencePnpJobProcessor.lblPlannerStrategy.text")); //$NON-NLS-1$
        lblPlannerStrategy.setToolTipText(Translations.getString("MachineSetup.JobProcessors.ReferencePnpJobProcessor.lblPlannerStrategy.toolTipText")); //$NON-NLS-1$
        panelGeneral.add(lblPlannerStrategy, "2, 4, right, default");

        comboBoxPlannerStrategy = new JComboBox<Strategy>(Strategy.values());
        panelGeneral.add(comboBoxPlannerStrategy, "4, 4");

        JLabel lblMaxVisionRetries = new JLabel(Translations.getString("MachineSetup.JobProcessors.ReferencePnpJobProcessor.Label.MaxVisionRetries")); //$NON-NLS-1$
        panelGeneral.add(lblMaxVisionRetries, "2, 6, right, default");

        maxVisionRetriesTextField = new JTextField();
        panelGeneral.add(maxVisionRetriesTextField, "4, 6");
        maxVisionRetriesTextField.setColumns(10);

        JLabel lblStepsMotion = new JLabel(Translations.getString("ReferencePnpJobProcessorConfigurationWizard.lblStepsMotion.text")); //$NON-NLS-1$
        lblStepsMotion.setToolTipText(Translations.getString("ReferencePnpJobProcessorConfigurationWizard.lblStepsMotion.toolTipText")); //$NON-NLS-1$
        panelGeneral.add(lblStepsMotion, "2, 8, right, default");

        steppingToNextMotion = new JCheckBox(); 
        panelGeneral.add(steppingToNextMotion, "4, 8");

        JLabel lblOptimizeMultipleNozzles = new JLabel(Translations.getString("ReferencePnpJobProcessorConfigurationWizard.lblOptimizeMultipleNozzles.text")); //$NON-NLS-1$
        lblOptimizeMultipleNozzles.setToolTipText(Translations.getString("ReferencePnpJobProcessorConfigurationWizard.lblOptimizeMultipleNozzles.toolTipText")); //$NON-NLS-1$
        panelGeneral.add(lblOptimizeMultipleNozzles, "2, 10, right, default");

        optimizeMultipleNozzles = new JCheckBox(); 
        panelGeneral.add(optimizeMultipleNozzles, "4, 10");

        JLabel lblPreRotateAllNozzles = new JLabel(Translations.getString("ReferencePnpJobProcessorConfigurationWizard.lblPreRotateAllNozzles.text")); //$NON-NLS-1$
        lblPreRotateAllNozzles.setToolTipText(Translations.getString("ReferencePnpJobProcessorConfigurationWizard.lblPreRotateAllNozzles.toolTipText")); //$NON-NLS-1$
        panelGeneral.add(lblPreRotateAllNozzles, "2, 12, right, default");

        preRotateAllNozzles = new JCheckBox(); 
        panelGeneral.add(preRotateAllNozzles, "4, 12");

        JPanel panelFeedOptimalization = new JPanel();
        panelFeedOptimalization.setBorder(new TitledBorder(null, Translations.getString(
                "ReferencePnpJobProcessorConfigurationWizard.FeedOptimalizationPanel.Border.title"),
                TitledBorder.LEADING, TitledBorder.TOP, null, null));
        contentPanel.add(panelFeedOptimalization);
        panelFeedOptimalization.setLayout(new FormLayout(new ColumnSpec[] {
                FormSpecs.RELATED_GAP_COLSPEC,
                FormSpecs.DEFAULT_COLSPEC,
                FormSpecs.RELATED_GAP_COLSPEC,
                FormSpecs.DEFAULT_COLSPEC,},
                new RowSpec[] {
                        FormSpecs.RELATED_GAP_ROWSPEC,
                        FormSpecs.DEFAULT_ROWSPEC,
                        FormSpecs.RELATED_GAP_ROWSPEC,
                        FormSpecs.DEFAULT_ROWSPEC,
                        FormSpecs.RELATED_GAP_ROWSPEC,
                        FormSpecs.RELATED_GAP_ROWSPEC,
                        FormSpecs.DEFAULT_ROWSPEC,}));

        JLabel lblUseAsyncFeed = new JLabel(Translations.getString("ReferencePnpJobProcessorConfigurationWizard.lblUseAsyncFeed.text")); //$NON-NLS-1$
        lblUseAsyncFeed.setToolTipText(Translations.getString("ReferencePnpJobProcessorConfigurationWizard.lblUseAsyncFeed.toolTipText")); //$NON-NLS-1$
        panelFeedOptimalization.add(lblUseAsyncFeed, "2, 2, right, default");

        useAsyncFeed = new JCheckBox();
        panelFeedOptimalization.add(useAsyncFeed, "4, 2");

        JLabel lblFeedAfterPick = new JLabel(Translations.getString("ReferencePnpJobProcessorConfigurationWizard.lblFeedAfterPick.text")); //$NON-NLS-1$
        lblFeedAfterPick.setToolTipText(Translations.getString("ReferencePnpJobProcessorConfigurationWizard.lblFeedAfterPick.toolTipText")); //$NON-NLS-1$
        panelFeedOptimalization.add(lblFeedAfterPick, "2, 4, right, default");

        feedAfterPick = new JCheckBox();
        feedAfterPick.setEnabled(useAsyncFeed.isSelected());
        panelFeedOptimalization.add(feedAfterPick, "4, 4");

        /*
         * This logic is important to keep code of job processor tidy.
         * Settings FAP without Async have no use case and is disabled here.
         * If for some reason FAP is enabled without async, the job processor may become unpredictable.
         * However - should be properly handled in ReferencePnpJobProcessor.
         */
        useAsyncFeed.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                switch (e.getStateChange()) {
                    case ItemEvent.DESELECTED:
                        if (feedAfterPick.isSelected()) {
                            feedAfterPick.doClick();
                        }
                    case ItemEvent.SELECTED:
                        feedAfterPick.setEnabled(useAsyncFeed.isSelected());
                        break;
                    default:
                }
            }
        });

        JLabel lblAvoidConsecutivePicksFromSameFeeder = new JLabel(Translations.getString("ReferencePnpJobProcessorConfigurationWizard.lblAvoidConsecutivePicksFromSameFeeder.text")); //$NON-NLS-1$
        lblAvoidConsecutivePicksFromSameFeeder.setToolTipText(Translations.getString("ReferencePnpJobProcessorConfigurationWizard.lblAvoidConsecutivePicksFromSameFeeder.toolTipText")); //$NON-NLS-1$
        panelFeedOptimalization.add(lblAvoidConsecutivePicksFromSameFeeder, "2, 7, right, default");

        avoidConsecutivePicksFromSameFeeder = new JCheckBox();
        panelFeedOptimalization.add(avoidConsecutivePicksFromSameFeeder, "4, 7");

    }

    @Override
    public void createBindings() {
        IntegerConverter intConverter = new IntegerConverter();

        addWrappedBinding(jobProcessor, "jobOrder", comboBoxJobOrder, "selectedItem");
        addWrappedBinding(jobProcessor.planner, "strategy", comboBoxPlannerStrategy, "selectedItem");
        addWrappedBinding(jobProcessor, "maxVisionRetries", maxVisionRetriesTextField, "text", intConverter);
        addWrappedBinding(jobProcessor, "steppingToNextMotion", steppingToNextMotion, "selected");
        addWrappedBinding(jobProcessor, "optimizeMultipleNozzles", optimizeMultipleNozzles, "selected");
        addWrappedBinding(jobProcessor, "preRotateAllNozzles", preRotateAllNozzles, "selected");
        addWrappedBinding(jobProcessor, "feedAfterPick", feedAfterPick, "selected");
        addWrappedBinding(jobProcessor, "useAsyncFeed", useAsyncFeed, "selected");
        addWrappedBinding(jobProcessor, "avoidConsecutivePicksFromSameFeeder", avoidConsecutivePicksFromSameFeeder, "selected");

        ComponentDecorators.decorateWithAutoSelect(maxVisionRetriesTextField);
    }
}
